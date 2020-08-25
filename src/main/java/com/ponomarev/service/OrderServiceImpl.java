package com.ponomarev.service;

import com.ponomarev.entity.Order;
import com.ponomarev.web.dto.CreateOrderDto;
import com.ponomarev.web.dto.OrderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

     // All code placed here is just for little demo.

    private final JpaRepository<Order,Long> orderRepository;

    @Override
    @Transactional
    public Long createOrder(CreateOrderDto createOrderDto) {
        Order newOrder = new Order();
        newOrder.setHash(UUID.randomUUID().toString());
        newOrder.setProducer(createOrderDto.getProducer());
        newOrder.setConsumer(createOrderDto.getConsumer());

        Order savedOrder = orderRepository.save(newOrder);
        return savedOrder.getId();
    }

    @Override
    @Transactional
    public OrderDto getOrder(Long id) {
        Order targetOrder = orderRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new);
        return convertOrder(targetOrder);
    }

    @Override
    @Transactional
    public List<OrderDto> getOrderList() {
        return orderRepository.findAll()
                .stream().map(this::convertOrder)
                .collect(Collectors.toList());
    }

    private OrderDto convertOrder(Order order) {
        OrderDto orderDto = new OrderDto();
        orderDto.setId(order.getId());
        orderDto.setProducer(order.getProducer());
        orderDto.setConsumer(order.getConsumer());
        orderDto.setHash(order.getHash());
        return orderDto;
    }

    @Override
    @Transactional
    public boolean removeOrder(Long id) {
    orderRepository.deleteById(id);
    return !orderRepository.existsById(id);
    }

}
