package me.zw.step01.order;

public interface OrderService {
    Order createOrder(Long memberId, String itemName, int itemPrice);
}
