package com.example.demo.shared;

import com.example.demo.model.Item;
import com.example.demo.model.ui.ItemDto;
import com.example.demo.model.ui.ItemExtendedDto;
import org.springframework.stereotype.Component;

@Component
public class ItemMapper implements Mapper<ItemDto, ItemExtendedDto, Item>{
    @Override
    public ItemDto toDto(Item entity) {
        return ItemDto.builder()
                      .id(entity.getId())
                      .name(entity.getName())
                      .unitPrice(entity.getUnitPrice())
                      .quantity(entity.getQuantity())
                      .build();
    }
    
    @Override
    public ItemExtendedDto toExtendedDto(Item entity) {
        return ItemExtendedDto.builder()
                              .id(entity.getId())
                              .name(entity.getName())
                              .description(entity.getDescription())
                              .unitPrice(entity.getUnitPrice())
                              .quantity(entity.getQuantity())
                              .build();
    }
    
    @Override
    public Item toEntity(ItemExtendedDto extendedDto) {
        return Item.builder()
                   .id(extendedDto.getId())
                   .name(extendedDto.getName())
                   .description(extendedDto.getDescription())
                   .unitPrice(extendedDto.getUnitPrice())
                   .quantity(extendedDto.getQuantity())
                   .build();
    }
}
