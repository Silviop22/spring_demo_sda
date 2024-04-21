package al.sdacademy.springdemo.order.service;

import al.sdacademy.springdemo.commons.fault.model.BadRequestException;
import al.sdacademy.springdemo.order.mapper.OrderMapper;
import al.sdacademy.springdemo.order.model.Order;
import al.sdacademy.springdemo.order.model.ExtendedOrderDto;
import al.sdacademy.springdemo.order.model.OrderDto;
import al.sdacademy.springdemo.order.model.OrderStatus;
import al.sdacademy.springdemo.order.repository.OrderRepository;
import al.sdacademy.springdemo.commons.util.ObjectPatcher;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    
    public OrderService(OrderRepository orderRepository, OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
    }
    
    @Transactional
    public ExtendedOrderDto getById(Long id) {
        return orderMapper.toDto(getExistingEntity(id));
    }
    
    @Transactional
    public Page<OrderDto> getList(int page, int size) {
        Pageable pageRequest = PageRequest.of(page, size);
        return orderRepository.findAll(pageRequest)
                       .map(orderMapper::toShortenedDto);
    }
    
    @Transactional
    public Long createOrder(ExtendedOrderDto orderDto) throws BadRequestException {
        if (orderDto.getOrderItems().isEmpty()) {
            throw new BadRequestException("Order must contain at least one item");
        }
        Order order = orderMapper.toEntity(orderDto);
        order.setStatus(OrderStatus.CREATED);
        return orderRepository.save(order).getId();
    }
    
    @Transactional
    public void updateOrder(ExtendedOrderDto orderDto, Long id) {
        Order existing = getExistingEntity(id);
        Order updateCandidate = orderMapper.toEntity(orderDto);
        if (isStatusUpdate(updateCandidate.getStatus(), existing.getStatus())) {
            // TODO: Implement status update logic
        }
        ObjectPatcher.patchObject(updateCandidate, existing);
        orderRepository.save(existing);
    }
    
    @Transactional
    public void deleteOrder(Long id) {
        getExistingEntity(id);
        orderRepository.deleteById(id);
    }
    
    private Order getExistingEntity(Long id) {
        return orderRepository.findById(id)
                       .orElseThrow();
    }
    
    private boolean isStatusUpdate(OrderStatus target, OrderStatus current) {
        return target != null && target != current;
    }
}
