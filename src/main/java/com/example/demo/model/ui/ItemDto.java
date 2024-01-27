package com.example.demo.model.ui;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class ItemDto {
    private Long id;
    private String name;
    private BigDecimal unitPrice;
    private Integer quantity;
}
