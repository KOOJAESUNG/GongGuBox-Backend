package com.gonggubox.service.order;

import com.gonggubox.domain.member.MemberEntity;
import com.gonggubox.domain.order.OrderEntity;
import com.gonggubox.dto.order.OrderDto;
import com.gonggubox.mapper.order.OrderMapper;
import com.gonggubox.repository.member.MemberRepository;
import com.gonggubox.repository.order.OrderItemRepository;
import com.gonggubox.repository.order.OrderRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class OrderService {

    private final OrderRepository orderRepository;

    private final OrderItemRepository orderItemRepository;

    private final OrderMapper orderMapper;

    private final MemberRepository memberRepository;

    @Transactional
    public OrderDto.OrderResponseDto createOrder(Long memberId, OrderDto.OrderPostDto orderPostDto) {
        MemberEntity member = memberRepository.findById(memberId).orElseThrow(EntityNotFoundException::new);
        OrderEntity order = orderMapper.toEntity(orderPostDto, member);
        return orderMapper.toResponseDto(orderRepository.save(order));
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
