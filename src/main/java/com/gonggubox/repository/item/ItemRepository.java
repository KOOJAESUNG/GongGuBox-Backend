package com.gonggubox.repository.item;


import com.gonggubox.domain.item.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ItemRepository extends JpaRepository<ItemEntity,Long> {

    Optional<Long> getPriceById(Long itemId);
}
