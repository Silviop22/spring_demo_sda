package com.example.demo.model.ui;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ItemDto {
    private Long id;
    private String name;
    private Float unitPrice;
    private Integer quantity;
}
