package com.ponomarev;

import com.ponomarev.entity.Order;
import com.ponomarev.service.OrderServiceImpl;
import com.ponomarev.web.dto.CreateOrderDto;
import com.ponomarev.web.dto.OrderDto;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceImplTest {

    private static final long TEST_ORDER_ID = 1L;
    private static final int RANDOM_STRING_LENGTH = 5;

    @InjectMocks
    private OrderServiceImpl orderService;

    @Mock
    private JpaRepository<Order,Long> orderRepository;

    @Test
    public void shouldCreateOrder() {
        CreateOrderDto createOrderDto = new CreateOrderDto(RandomStringUtils.randomAlphabetic(RANDOM_STRING_LENGTH),
                RandomStringUtils.randomAlphabetic(RANDOM_STRING_LENGTH));
        Order expectedCreatedOrder = convertToOrder(createOrderDto);
        Order responseOrder = new Order();
        responseOrder.setId(TEST_ORDER_ID);
        when(orderRepository.save(expectedCreatedOrder)).thenReturn(responseOrder);

        orderService.createOrder(createOrderDto);

        verify(orderRepository).save(expectedCreatedOrder);
    }

    @Test
    public void shouldObtainOrder() {
        when(orderRepository.findById(TEST_ORDER_ID)).thenReturn(Optional.of(generateOrder()));

        OrderDto order = orderService.getOrder(TEST_ORDER_ID);

        assertNotNull(order);
        assertEquals(order.getId(), TEST_ORDER_ID);
        assertNotNull(order.getHash());
        assertNotNull(order.getConsumer());
        assertNotNull(order.getProducer());
    }

    @Test
    public void shouldDeleteOrder() {
        orderService.removeOrder(TEST_ORDER_ID);

        verify(orderRepository).deleteById(TEST_ORDER_ID);
    }

    private Order generateOrder() {
        Order order = new Order();
        order.setId(TEST_ORDER_ID);
        order.setHash(UUID.randomUUID().toString());
        order.setProducer(RandomStringUtils.randomAlphabetic(RANDOM_STRING_LENGTH));
        order.setConsumer(RandomStringUtils.randomAlphabetic(RANDOM_STRING_LENGTH));
        return order;
    }

    private Order convertToOrder(CreateOrderDto createOrderDto) {
        Order order = new Order();
        order.setProducer(createOrderDto.getProducer());
        order.setConsumer(createOrderDto.getConsumer());
        return order;
    }


}
