package com.gonggubox.service.cart;

import com.gonggubox.dto.cart.CartDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class CartService {
    @Transactional
    public void createCart(CartDto.CartPostDto CartPostDto) {

    }

    public void getCart(Long CartId) {

    }
    @Transactional
    public void updateCart(CartDto.CartPatchDto CartPatchDto) {

    }
    @Transactional
    public void deleteCart(Long CartId) {

    }
}
