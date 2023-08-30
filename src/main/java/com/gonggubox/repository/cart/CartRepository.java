package com.gonggubox.repository.cart;


import com.gonggubox.domain.cart.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<CartEntity,Long> {

    Boolean existsByMemberId(Long memberId);

    Optional<CartEntity> findByMemberId(Long memberId);

}
