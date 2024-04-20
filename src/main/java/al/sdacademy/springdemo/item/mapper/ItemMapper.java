package al.sdacademy.springdemo.item.mapper;

import al.sdacademy.springdemo.item.model.Item;
import al.sdacademy.springdemo.item.model.ItemDto;
import al.sdacademy.springdemo.util.Mapper;
import org.springframework.stereotype.Component;

@Component
public class ItemMapper implements Mapper<Item, ItemDto> {
    @Override
    public ItemDto toDto(Item item) {
        return new ItemDto.Builder()
                       .id(item.getId())
                       .name(item.getName())
                       .description(item.getDescription())
                       .unitPrice(item.getUnitPrice())
                       .quantity(item.getQuantity())
                       .imageUrl(item.getImageUrl())
                       .build();
    }
    
    @Override
    public Item toEntity(ItemDto itemDto) {
        return Item.builder()
                       .id(itemDto.getId())
                       .name(itemDto.getName())
                       .description(itemDto.getDescription())
                       .unitPrice(itemDto.getUnitPrice())
                       .quantity(itemDto.getQuantity())
                       .imageUrl(itemDto.getImageUrl())
                       .build();
    }
}
