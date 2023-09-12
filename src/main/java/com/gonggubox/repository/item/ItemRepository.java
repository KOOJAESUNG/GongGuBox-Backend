package com.gonggubox.repository.item;


import com.gonggubox.domain.item.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ItemRepository extends JpaRepository<ItemEntity,Long> {

    @Query("select i.price from ItemEntity i where i.id = :itemId")
    Optional<Long> getPriceById(Long itemId);
}
