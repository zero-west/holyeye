package me.zw.actualjpa1.service;

import lombok.RequiredArgsConstructor;
import me.zw.actualjpa1.domain.Delivery;
import me.zw.actualjpa1.domain.Member;
import me.zw.actualjpa1.domain.Order;
import me.zw.actualjpa1.domain.OrderItem;
import me.zw.actualjpa1.domain.item.Item;
import me.zw.actualjpa1.repository.ItemRepository;
import me.zw.actualjpa1.repository.MemberRepository;
import me.zw.actualjpa1.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    private final MemberRepository memberRepository;

    private final ItemRepository itemRepository;

    // 주문
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

        // 주문 저장 (원래는 Delivery, orderItem 각각 넣어줘야 하지만
        orderRepository.save(order);

        return order.getId();
    }

    // 주문 취소
    @Transactional
    public void cancelOrder(Long orderId) {
        // 주문 엔티티 조회
        Order order = orderRepository.findOne(orderId);

        // 주문 취소
        order.cancel();
    }

    // 검색
//    public List<Order> findOrders(OrderSearch orderSearch) {
//        return orderRepository.findAll(orderSearch);
//    }
}
