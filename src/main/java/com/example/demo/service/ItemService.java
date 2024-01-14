package com.example.demo.service;

import com.example.demo.model.Item;
import com.example.demo.model.ui.ItemExtendedDto;
import com.example.demo.repository.ItemRepository;
import com.example.demo.shared.ItemMapper;
import com.example.demo.shared.UserMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository repository;
    private final ItemMapper mapper;
    
    public ItemExtendedDto getItemById(Long id) {
        Item item = repository.findById(id)
                              .orElseThrow(() -> new EntityNotFoundException("Item with id " + id + " not found"));
        return mapper.toExtendedDto(item);
    }
    
}
