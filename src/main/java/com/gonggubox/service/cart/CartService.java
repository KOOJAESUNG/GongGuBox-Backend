package com.gonggubox.service.cart;

import com.gonggubox.domain.cart.CartEntity;
import com.gonggubox.mapper.cart.CartMapper;
import com.gonggubox.repository.cart.CartItemRepository;
import com.gonggubox.repository.cart.CartRepository;
import com.gonggubox.repository.item.ItemRepository;
import com.gonggubox.repository.member.MemberRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class CartService {

    private final CartRepository cartRepository;

    private final CartMapper cartMapper;

    private final MemberRepository memberRepository;

    private final ItemRepository itemRepository;

    private final CartItemRepository cartItemRepository;


    public CartEntity getCart(Long memberId) {
        if (cartRepository.existsByMemberId(memberId)) {
            return cartRepository.findByMemberId(memberId).orElseThrow(EntityNotFoundException::new); //만약 이미 장바구니가 있다면 장바구니 리턴
        } else { //만약 장바구니가 없다면 만들어서 저장 후 리턴
            CartEntity cart = CartEntity.createCartEntity(memberRepository.findById(memberId).orElseThrow(EntityNotFoundException::new));
            return cartRepository.save(cart);
        }

    }

    @Transactional
    public CartEntity addItemToCart(Long memberId, Long itemId, Integer count) {
        CartEntity cart = getCart(memberId);
        if (!cart.addItem(itemRepository.findById(itemId).orElseThrow(EntityNotFoundException::new), count)) {
            throw new RuntimeException("이미 상품이 장바구니에 존재합니다.");
        }
        return cart;
    }

    @Transactional
    public String deleteItemInCart(Long memberId, Long itemId) {
        CartEntity cart = getCart(memberId);
        return "삭제된 상품 : " + cart.deleteItem(itemRepository.findById(itemId).orElseThrow(EntityNotFoundException::new));
    }

    @Transactional
    public CartEntity updateItemCountInCart(Long memberId, Long itemId, Integer count) {
        CartEntity cart = getCart(memberId);
        cart.updateItemCount(itemRepository.findById(itemId).orElseThrow(EntityNotFoundException::new), count);
        return cart;
    }

    @Transactional
    public String clearCart(Long memberId) {
        return getCart(memberId).clearCart();
    }

}
