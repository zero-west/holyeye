# 실전! 스프링 부트와 JPA 활용 - 1

##### 김영한님의 '실전! 스프링 부트와 JPA 활용 - 1' 을 듣고 다시 살펴볼 부분을 필기한 자료입니다.



## 프로젝트 환경 설정

#### - application.yaml

```yaml
spring:
  datasource:
    url: jdbc:h2:tcp://localhost/~/jpashop;
    username: sa
    driver-class-name: org.h2.Driver

  jpa:
    hibernate:
      ddl-auto: create
    properties:
      hibernate:
        show_sql: true
        format_sql: true

logging:
  level:
    org.hiberante.SQL: debug
    org.hibernate.type: trace
```



## 도메인 분석 설계

#### _1) 양방향 관계는 연관관계의 주인을 정해줘야 한다._

> **외래 키가 있는 쪽을 연관관계의 주인으로 정해라 !!**
>
> * 연관관계의 주인은 단순히 외래 키를 누가 관리하냐의 문제일뿐 비즈니스 상의 우위 여부가 아니다.
> * 외래키가 없는 쪽을 주인으로 정하면 추가적인 별도의 업데이트 쿼리가 발생하는 성능 문제가 있다.
> * 주인이 아닌 쪽은 @OneToMany 에 mappedBy="{연관테이블의필드명}" 속성 사용해서 정해준다.

#### _2) 엔티티 클래스 설계시, Setter 는 꼭 필요한 경우에만 사용하자._

> **이론적으로는 Getter, Setter 모두 제공하지 않고, 꼭 필요한 별도의 메서드를 제공하는 것이 이상적.**
>
> * 실무에서는 엔티티의 데이터는 조회할 일이 너무 많으므로, Getter 는 모두 열어두는것이 편리.
> * Setter 는 호출시 데이터가 변하므로 막 열어두면 엔티티가 왜 변경되는지 추적하기가 점점 힘들다.
> * 엔티티를 변경할 때는 변경 지점이 명확하도록 변경을 위한 비즈니스 메서드를 별도로 제공하라.

#### _3) 값 타입은 변경 불가능하게 설계해라._

>* Setter 를 만들지 말고, 생성자에서 값을 모두 초기화해서 변경 불가능한 클래스를 만들자.
>* 엔티티나 임베디드 타입(@Embeddable) 은 자바 기본 생성자가 public 또는 protected여야 한다.
>* public 보다는 protected 가 좀 더 안전하다.
>* JPA가 이런 제약을 두는 이유는 JPA 스펙을 구현할 때 객체 생성시 리플렉션을 활용하기 때문이다.

#### _4) 모든 연관관계는 지연로딩 (LAZY)으로 설정하라_

>**즉시로딩 (EAGER)은 어떤 SQL이 실행될지 추적하기 어렵다.**
>
>* 특히 JPQL 을 실행할 때 N+1 문제가 자주 발생한다.
>* 연관된 엔티티를 함께 조회해야 하면, fetch join 또는 엔티티 그래프 기능을 사용하라.
>* **XToOne 관계는 기본이 즉시로딩이므로 직접 지연로딩으로 설정해야 한다.**
>* @OneToMany 는 기본전략이 LAZY

#### _5) 컬렉션은 필드에서 초기화 하라._

>* null 문제에서 안전하다
>
>* 하이버네이트는 엔티티를 영속화 할 때, 컬렉션을 하이버네이트 자체적인 컬렉션으로 변경한다.
>
>  ```java
>  Member member = new Member();
>  em.persist(team);
>  System.out.println(member.getOrders().getClass());
>  ```
>
>  _결과: class org.hibernate.collection.internal.PersistentBag_

#### _6) 셀프 조인_

```java
@Entity
public class Category {
    @Id
    @GeneratedValue
    @Column(name = "category_id")
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Category parent;

    @OneToMany(mappedBy = "parent")
    private List<Category> child = new ArrayList<>();
}
```

#### _7) 상속 관계_

>**상속관계는 @Inheritance의 strategy 속성으로 전략을 설정해준다.**
>
>* SINGLE_TABLE: 통합 테이블로 변환
>* JOINED: 각각 테이블로 변환
>* TABLE_PER_CLASS: 서브타입 테이블로 변환 (사용하지 않는 것을 추천)
>
>**@DiscriminatorValue** 어노테이션은 부모가 자식을 구분하기 위한 값을 지정하는 역할을 합니다.

#### _7) 기타_

> * **Enum 타입은 JPA에서 @Enumerated(EnumType.STRING) 를 반드시 설정해줘야 한다.**
>   * EnumType.ORDINAL 의 경우 순서가 바뀌면 오류 발생 가능성이 생긴다.
> * **일대일 양방향 관계에서 주인은 주로 조회를 시작할 지점으로 정한다.**



## 회원 도메인 개발

#### _1) @Transactional 어노테이션_

>**스프링이 제공하는 @Transactional 어노테이션을 사용하자**
>
>* 조회만 하는 경우 readOnly 옵션을 true로 설정하면 성능이 향상된다.

#### _2) 회원가입테스트_

```java
@Test
@Transactional
public void 회원가입_테스트() throws Exception {
    // given
    Member member = new Member();
    member.setName("kim");
    // when
    Long savedId = memberService.join(member);
    // then
    assertEquals(member, memberRepository.findOne(savedId));
}
```

> * 같은 트랜잭션 안에서 id(PK) 값이 같으면 같은 영속성 컨텍스트에서 똑같이 관리된다.
> * 실제로 위 코드를 실행하면 insert 쿼리가 나가지 않는다.
>   * @Transactional 어노테이션은 기본적으로 커밋하지 않고 롤백한다. (flush X)

#### _3) 중복 회원 테스트_

```java
@Test
@Transactional
public void 중복_회원_예외() throws Exception {
    // given
    Member member1 = new Member();
    member1.setName("kim");
    Member member2 = new Member();
    member2.setName("kim");
    // when
    memberService.join(member1);
    // then
    assertThrows(IllegalStateException.class, () -> memberService.join(member2));
}
```

#### _4) 인메모리 DB로 테스트하기_

> **src/test 에 resource 디렉토리를 만들어주고 application.properties 을 복사한 뒤 수정한다.**
>
> * spring.datasource.url=jdbc:h2:mem:test 로 수정
> * 스프링 부트에서는 따로 설정을 안하면 기본적으로 인메모리 DB 로 테스트한다.



## 주문 도메인 개발

#### _1) OrderService 살펴보기_

```java
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;

    @Transactional
    public Long order(Long memberId, Long itemId, int itemCount) {
        // 엔티티 조회
        Member member = memberRepository.findOne(memberId);
        Item item = itemRepository.findOne(itemId);

        // 배송정보 생성
        Delivery delivery = new Delivery();
        delivery.setAddress(member.getAddress());

        // 주문상품 생성
        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), itemCount);

        // 주문 생성
        Order order = Order.createOrder(member, delivery, orderItem);

        // 주문 저장 
        orderRepository.save(order);    
        return order.getId();
    }
    
    @Transactional
    public void cancelOrder(Long orderId) {
        // 주문 엔티티 조회
        Order order = orderRepository.findOne(orderId);
        // 주문 취소
        order.cancel();
    }
}
```

>**Cascade 옵션**
>
>* 원래는 delivery, orderItem 각각 리포지토리를 만들어서 넣어줘야 한다! <br>그런데 여기서는 order 만 넣어줬다.
> * **Why?**
>   * 엔티티 설계시 cascade 옵션을 통해 설정해줬기 때문이다
>   * 결국 Order 가 일종의 DDD 에서 말하는 Aggregate Root 로 볼 수있다
>   * 나머지랑 생명주기가 같기 때문에 쓸 수 있다.<br>
>
>**Protected 생성자**
>
>* 엔티티에 protected 생성자를 추가하자.
> * **Why?**
>   * 코드에서 주문 생성할 때 DDD의 팩토리 패턴을 활용하고 있다.
>   * new 와 setter 를 이용한 방식을 막아야 한다.
>
>**Dirty Checking**
>
>* order.cancel() 만 한다?
> * **Why?**
>   * cancel() 메서드는 도메인모델 패턴으로 일종의 퍼사드 처럼 되어있다.
>   * 다른 엔티티들은 JPA 가 알아서 Dirty Checking 을 통해 변경사항을 반영한다.
>   * ORM 을 사용하지 않으면 일일히 업데이트 쿼리를 날려줘야 한다.
>   * 의미 있는 단위 테스트를 작성할 수 있게 해준다.



## 웹 계층 개발

#### _1) Data Transfer Object_

```java
@Controller
@RequiredArgsConstructor
public class MemberController {
    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
```

> * members 로 바로 엔티티들의 리스트를 모델로 담아서 반환했다
>   * **Why?**
>     * 서버사이드에서 타임리프를 이용하여 처리하고 간단해서 택했다.
>     * 일반적으로 Data Transfer Object 객체를 만들어서 유지보수가 용이토록 한다.
>     * 그러나 API 개발 시에는 절대로 이런 방식을 택해서는 안된다.
>     * 엔티티는 핵심 비즈니스 로직만 가지고 화면을 위한 로직은 가져서는 안 된다.



#### _2) 변경 감지(Dirty checking)와 병합(merge)_

```java
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;

    @Transactional
    public void updateItem(Long itemId, String name, int price, int stockQuantity) {
        Item findItem = itemRepository.findOne(itemId);
        findItem.setName(name);
        findItem.setPrice(price);
        findItem.setStockQuantity(stockQuantity);
    }
}
```

>* 병합보다는 **변경 감지**를 쓰는 것이 더 좋은 선택이다.
>
>  * **Why?**
>    - 병합을 선택하면 준영속 엔티티의 식별자로 영속 엔티티를 조회해와서 수정한다
>    - Dirty checking 은 원하는 속성만 변경 가능하지만 병합은 전부 변경된다.
>    - 따라서 값이 없으면 null 로 업데이트 할 위험이 있다.
>
>  * **결론**
>    * 트랜잭션이 있는 서비스 계층에 식별자 와 변경할 데이터를 전달해라.
>    * 이 때 파라미터나 dto 를 활용한다.
>    * 서비스 계층에서 영속 상태 엔티티를 조회하고, 엔티티 데이터를 직접 변경해라.

 