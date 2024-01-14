package com.example.demo.model.ui;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ItemExtendedDto {
    private Long id;
    private String name;
    private String description;
    private Float unitPrice;
    private Integer quantity;
}
