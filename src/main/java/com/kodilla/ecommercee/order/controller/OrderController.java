package com.kodilla.ecommercee.order.controller;

import com.kodilla.ecommercee.order.domain.OrderDto;
import com.kodilla.ecommercee.order.mapper.OrderMapper;
import com.kodilla.ecommercee.order.service.OrderDbService;
import com.kodilla.ecommercee.user.controller.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/orders")
public class OrderController {

    private OrderDbService service;

    @Autowired
    public void setService(OrderDbService service) {
        this.service = service;
    }

    private OrderMapper mapper;

    @Autowired
    public void setMapper(OrderMapper mapper) {
        this.mapper = mapper;
    }

    @GetMapping("getOrders")
    public List<OrderDto> getAllOrders() {
        return mapper.mapToOrderDtoList(service.getAllOrders());
    }

    @PostMapping("createOrder")
    public void createOrder(@RequestBody OrderDto orderDto) throws UserNotFoundException {
        service.saveOrder(mapper.mapToOrder(orderDto));
    }

    @GetMapping("getOrder")
    public OrderDto getOrder(@RequestParam Long orderId) throws OrderNotFoundException {
        return mapper.mapToOrderDto(service.getOrder(orderId).orElseThrow(OrderNotFoundException::new));
    }

    @PutMapping("updateOrder")
    public OrderDto updateOrder(@RequestBody OrderDto orderDto) throws UserNotFoundException {
        return mapper.mapToOrderDto(service.saveOrder(mapper.mapToOrder(orderDto)));
    }

    @DeleteMapping("deleteOrder")
    public void deleteOrder(@RequestParam Long orderId) {
        service.deleteOrder(orderId);
    }
}
