package com.example.demo.model.ui;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class ItemExtendedDto {
    private Long id;
    @NotBlank(message = "Name is mandatory")
    private String name;
    @NotBlank(message = "Description is mandatory")
    private String description;
    @NotNull(message = "Unit price is mandatory")
    @DecimalMin(value = "0.01", message = "Unit price must be greater than 0.00")
    private BigDecimal unitPrice;
    @NotNull(message = "Quantity is mandatory")
    private Integer quantity;
}
