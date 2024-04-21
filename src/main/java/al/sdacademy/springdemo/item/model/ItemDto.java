package al.sdacademy.springdemo.item.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemDto {
    private Long id;
    @NotBlank(message = "Name is mandatory",
            groups = ItemCreationValidation.class)
    private String name;
    @NotBlank(message = "Description is mandatory",
            groups = ItemCreationValidation.class)
    private String description;
    @NotNull(message = "Unit price is mandatory",
            groups = ItemCreationValidation.class)
    private BigDecimal unitPrice;
    @NotNull(message = "Quantity is mandatory",
            groups = ItemCreationValidation.class)
    private Integer quantity;
    @NotBlank(message = "Image URL is mandatory",
            groups = ItemCreationValidation.class)
    private String imageUrl;
}
