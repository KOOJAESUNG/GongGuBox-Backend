package com.gonggubox.repository.cart;


import com.gonggubox.domain.cart.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<CartEntity,Long> {
}
