package al.sdacademy.springdemo.order.model;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@Builder
public class ExtendedOrderDto {
    private Long id;
    private LocalDateTime creationDate;
    private OrderStatus status;
    @NotNull(message = "Customer ID is mandatory", groups = OrderCreationValidation.class)
    private Long customerId;
    @NotNull(message = "Order items are mandatory", groups = OrderCreationValidation.class)
    private Set<OrderItemDto> orderItems;
}
