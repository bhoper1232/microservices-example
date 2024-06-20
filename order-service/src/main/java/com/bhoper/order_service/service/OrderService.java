package com.bhoper.order_service.service;

import com.bhoper.order_service.dto.OrderLineItemsDto;
import com.bhoper.order_service.dto.OrderRequest;
import com.bhoper.order_service.model.Order;
import com.bhoper.order_service.model.OrderLineItems;
import com.bhoper.order_service.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;

    public void placeOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

        List<OrderLineItems> orderLineItems = orderRequest.orderLineItemsDtoList().stream()
                .map(this::mapToOrderLineItems)
                .toList();

        order.setOrderLineItemsList(orderLineItems);

        orderRepository.save(order);
    }

    private OrderLineItems mapToOrderLineItems(OrderLineItemsDto orderLineItemsDto) {
        return new OrderLineItems().builder()
                .price(orderLineItemsDto.price())
                .quantity(orderLineItemsDto.quantity())
                .skuCode(orderLineItemsDto.skuCode())
                .build();
    }

}
