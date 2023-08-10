package com.gonggubox.service.item;

import com.gonggubox.dto.item.ItemDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class ItemService {
    @Transactional
    public void createItem(ItemDto.ItemPostDto ItemPostDto) {

    }

    public void getItem(Long ItemId) {

    }
    @Transactional
    public void updateItem(ItemDto.ItemPatchDto ItemPatchDto) {

    }
    @Transactional
    public void deleteItem(Long ItemId) {

    }
}