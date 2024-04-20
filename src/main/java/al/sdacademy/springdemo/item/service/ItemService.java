package al.sdacademy.springdemo.item.service;

import al.sdacademy.springdemo.item.mapper.ItemMapper;
import al.sdacademy.springdemo.item.model.Item;
import al.sdacademy.springdemo.item.model.ItemDto;
import al.sdacademy.springdemo.item.repository.ItemRepository;
import al.sdacademy.springdemo.util.ObjectPatcher;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ItemService {
    private final ItemRepository itemRepository;
    private final ItemMapper itemMapper;
    
    public ItemService(ItemRepository itemRepository, ItemMapper itemMapper) {
        this.itemRepository = itemRepository;
        this.itemMapper = itemMapper;
    }
    
    @Transactional
    public ItemDto getById(Long id) {
        return itemMapper.toDto(itemRepository.findById(id)
                        .orElseThrow());
    }
    
    @Transactional
    public Page<ItemDto> getList(int page, int size) {
        Pageable pageble = PageRequest.of(page, size);
        return itemRepository.findAll(pageble)
                       .map(itemMapper::toDto);
    }
    
    @Transactional
    public ItemDto create(ItemDto itemDto) {
        String name = itemDto.getName();
        if (itemRepository.findByName(name)
                        .isPresent()) {
            throw new IllegalArgumentException();
        }
        
        Item item = itemMapper.toEntity(itemDto);
        item = itemRepository.save(item);
        itemDto.setId(item.getId());
        
        return itemDto;
    }
    
    @Transactional
    public void update(ItemDto itemDto, Long id) {
        Item existing = getExistingEntity(id);
        Item updateCandiate = itemMapper.toEntity(itemDto);
        ObjectPatcher.patchObject(updateCandiate, existing);
        itemRepository.save(existing);
    }
    
    @Transactional
    public void deleteById(Long id) {
        getExistingEntity(id);
        itemRepository.deleteById(id);
    }
    
    private Item getExistingEntity(Long id) {
        return itemRepository.findById(id)
                       .orElseThrow();
    }
}
