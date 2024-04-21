package al.sdacademy.springdemo.order.controller;

import al.sdacademy.springdemo.order.model.ExtendedOrderDto;
import al.sdacademy.springdemo.order.model.OrderDto;
import al.sdacademy.springdemo.order.service.OrderService;
import al.sdacademy.springdemo.commons.fault.model.BadRequestException;
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
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;
    
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ExtendedOrderDto> getOrderById(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.getById(id));
    }
    
    @GetMapping
    public ResponseEntity<List<OrderDto>> getList(@RequestParam int page, @RequestParam int size) {
        Page<OrderDto> result = orderService.getList(page, size);
        return ResponseEntity.ok(result.getContent());
    }
    
    @PostMapping
    public ResponseEntity<Object> createOrder(@RequestBody ExtendedOrderDto orderDto) throws BadRequestException {
        Long id = orderService.createOrder(orderDto);
        URI location = URI.create("/orders/" + id);
        return ResponseEntity.created(location)
                       .build();
    }
    
    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateOrder(@RequestBody ExtendedOrderDto orderDto, @PathVariable Long id) {
        orderService.updateOrder(orderDto, id);
        return ResponseEntity.noContent()
                       .build();
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return ResponseEntity.noContent()
                       .build();
    }
}
