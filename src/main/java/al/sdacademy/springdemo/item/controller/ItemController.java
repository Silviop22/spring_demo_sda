package al.sdacademy.springdemo.item.controller;

import al.sdacademy.springdemo.item.service.ItemService;
import al.sdacademy.springdemo.item.model.ItemDto;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemController {
    
    private final ItemService itemService;
    
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ItemDto> getItemById(@PathVariable Long id) {
        return ResponseEntity.ok(itemService.getById(id));
    }
    
    @GetMapping
    public ResponseEntity<List<ItemDto>> getItems(@RequestParam("page") int page, @RequestParam("size") int size) {
        Page<ItemDto> result = itemService.getList(page, size);
        return ResponseEntity.ok(result.getContent());
    }
    
    @PostMapping
    public ResponseEntity<Void> createItem(@Valid @RequestBody ItemDto itemDto) {
        ItemDto result = itemService.create(itemDto);
        return ResponseEntity.created(URI.create("/items/" + result.getId()))
                        .build();
    }
    
    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateItem(@RequestBody ItemDto itemDto, @PathVariable Long id) {
        itemService.update(itemDto, id);
        return ResponseEntity.noContent()
                        .build();
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long id) {
        itemService.deleteById(id);
        return ResponseEntity.noContent()
                        .build();
    }
}
