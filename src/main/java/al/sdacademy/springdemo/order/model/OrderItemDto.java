package al.sdacademy.springdemo.order.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderItemDto {
    private Long itemId;
    private Integer requestedQuantity;
}
