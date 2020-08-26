package com.ponomarev.web.controller;

import com.ponomarev.service.OrderService;
import com.ponomarev.web.dto.CreateOrderDto;
import com.ponomarev.web.dto.OrderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1")
public class OrderController {

    private final OrderService orderService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/order", consumes = "application/json")
    public ResponseEntity<Long> postOrder(@RequestBody CreateOrderDto createOrderDto) {
        Long newOrderId = orderService.createOrder(createOrderDto);
        return ResponseEntity.status(201).body(newOrderId);
    }

    @GetMapping(value = "/order/{orderId}", produces = "application/json")
    public ResponseEntity<OrderDto> getOrder(@PathVariable Long orderId) {
        return ResponseEntity.ok(orderService.getOrder(orderId));
    }

    @GetMapping(value = "/orders", produces = "application/json")
    public ResponseEntity<List<OrderDto>> getOrderList() {
        return ResponseEntity.ok(orderService.getOrderList());
    }

    @DeleteMapping(value = "/order/{orderId}")
    public ResponseEntity<String> deleteOrder(@PathVariable Long orderId) {
        boolean isRemoved = orderService.removeOrder(orderId);
        return ResponseEntity.ok(isRemoved ? "OK" : "FAIL");
    }
}
