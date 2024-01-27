package com.example.demo.service;

import com.example.demo.model.Item;
import com.example.demo.model.ui.ItemDto;
import com.example.demo.model.ui.ItemExtendedDto;
import com.example.demo.repository.ItemRepository;
import com.example.demo.repository.criteria.ItemSpecifications;
import com.example.demo.shared.ItemMapper;
import com.example.demo.shared.UserMapper;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

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
    
    public Set<ItemDto> getList() {
        return repository.findAll()
                         .stream()
                         .map(mapper::toDto)
                         .collect(Collectors.toSet());
    }
    
    public Item createItem(ItemExtendedDto item) {
        Specification<Item> existingCriteria = ItemSpecifications.getExistingSpecification(item);
        if (repository.findExisting(existingCriteria).isPresent()) {
            throw new EntityExistsException("Item with name " + item.getName() + " already exists");
        }
        return repository.save(mapper.toEntity(item));
    }
    
}
