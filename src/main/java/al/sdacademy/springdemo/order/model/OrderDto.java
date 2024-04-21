package al.sdacademy.springdemo.order.model;

import java.time.LocalDateTime;

public record OrderDto(Long id, LocalDateTime creationDate, OrderStatus status){
}
