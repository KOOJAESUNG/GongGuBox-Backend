package com.gonggubox.repository.cart;


import com.gonggubox.domain.cart.CartItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItemEntity,Long> {
}
