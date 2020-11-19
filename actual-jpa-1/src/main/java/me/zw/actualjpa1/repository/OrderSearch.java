package me.zw.actualjpa1.repository;

import lombok.Getter;
import lombok.Setter;
import me.zw.actualjpa1.domain.OrderStatus;

//검색 조건 파라미터
@Getter
@Setter
public class OrderSearch {

    private String memberName;          // 회원 이름

    private OrderStatus orderStatus;    // 주문 상태
}
