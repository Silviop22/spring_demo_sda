package al.sdacademy.springdemo.order.mapper;

import al.sdacademy.springdemo.item.model.Item;
import al.sdacademy.springdemo.order.model.ExtendedOrderDto;
import al.sdacademy.springdemo.order.model.OrderDto;
import al.sdacademy.springdemo.order.model.Order;
import al.sdacademy.springdemo.order.model.OrderItem;
import al.sdacademy.springdemo.order.model.OrderItemDto;
import al.sdacademy.springdemo.order.repository.OrderRepository;
import al.sdacademy.springdemo.user.model.User;
import al.sdacademy.springdemo.commons.util.Mapper;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class OrderMapper implements Mapper<Order, ExtendedOrderDto> {
    
    private final OrderRepository orderRepository;
    
    public OrderMapper(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }
    
    @Override
    public ExtendedOrderDto toDto(Order entity) {
        
        return ExtendedOrderDto.builder()
                       .id(entity.getId())
                       .creationDate(entity.getCreatedDate())
                       .customerId(entity.getCustomer().getId())
                       .status(entity.getStatus())
                       .orderItems(entity.getOrderItems()
                                      .stream()
                                      .map(this::mapOrderItem)
                                      .collect(Collectors.toSet()))
                       .build();
    }
    
    @Override
    public Order toEntity(ExtendedOrderDto dto) {
        Order entity = Order.builder()
                       .status(dto.getStatus())
                       .customer(User.builder()
                                         .id(dto.getCustomerId())
                                         .build())
                       .orderItems(dto.getOrderItems()
                                       .stream()
                                       .map(this::mapOrderItem)
                                       .collect(Collectors.toSet()))
                       .build();
        
        entity.getOrderItems()
              .forEach(orderItem -> orderItem.setOrder(entity));
        
        return entity;
    }
    
    public OrderDto toShortenedDto(Order entity) {
        return new OrderDto(entity.getId(), entity.getCreatedDate(), entity.getStatus());
    }
    
    private OrderItemDto mapOrderItem(OrderItem orderItem) {
        return OrderItemDto.builder()
                           .itemId(orderItem.getItem().getId())
                           .requestedQuantity(orderItem.getRequestedQuantity())
                           .build();
    }
    
    private OrderItem mapOrderItem(OrderItemDto orderItemDto) {
        Item item = Item.builder()
                            .id(orderItemDto.getItemId())
                            .build();
        
        return OrderItem.builder()
                       .item(item)
                       .requestedQuantity(orderItemDto.getRequestedQuantity())
                       .build();
    }
}
