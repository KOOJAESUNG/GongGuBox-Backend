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
        order.setOrderInOrderItems(); // cascade persist를 사용해 orderItems를 저장할 때 orderItem의 order를 지정하기 위한 메서드
        return orderMapper.toResponseDto(orderRepository.save(order));
    }

    public OrderDto.OrderResponseDto getOrder(Long orderId) {
        return orderMapper.toResponseDto(orderRepository.findById(orderId).orElseThrow(EntityNotFoundException::new));
    }


    @Transactional
    public void updateOrder(OrderDto.OrderPatchDto OrderPatchDto) {

    }

    @Transactional
    public String deleteOrder(Long orderId) {
        Long id = orderRepository.findById(orderId).orElseThrow(EntityNotFoundException::new).getId();
        orderRepository.deleteById(orderId);
        return "삭제한 Order의 id : " + id;
    }
}
