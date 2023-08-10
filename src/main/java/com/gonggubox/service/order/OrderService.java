package com.gonggubox.service.order;

import com.gonggubox.dto.order.OrderDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class OrderService {

    @Transactional
    public void createOrder(OrderDto.OrderPostDto OrderPostDto) {

    }

    public void getOrder(Long OrderId) {

    }

    @Transactional
    public void updateOrder(OrderDto.OrderPatchDto OrderPatchDto) {

    }

    @Transactional
    public void deleteOrder(Long OrderId) {

    }
}
