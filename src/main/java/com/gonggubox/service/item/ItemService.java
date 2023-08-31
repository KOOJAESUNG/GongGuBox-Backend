package com.gonggubox.service.item;

import com.gonggubox.dto.item.ItemDto;
import com.gonggubox.mapper.item.ItemMapper;
import com.gonggubox.repository.item.ItemRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class ItemService {

    private final ItemRepository itemRepository;

    private final ItemMapper itemMapper;

    @Transactional
    public ItemDto.ItemResponseDto createItem(ItemDto.ItemPostDto itemPostDto) {
        return itemMapper.toResponseDto(itemRepository.save(itemMapper.toEntity(itemPostDto)));
    }

    public ItemDto.ItemResponseDto getItem(Long itemId) {
        return itemMapper.toResponseDto(itemRepository.findById(itemId).orElseThrow(EntityNotFoundException::new));
    }

    @Transactional
    public ItemDto.ItemResponseDto updateItem(ItemDto.ItemPatchDto itemPatchDto) {
        itemMapper.updateFromPatchDto(itemPatchDto, itemRepository.findById(itemPatchDto.getId()).orElseThrow(EntityNotFoundException::new));
        return itemMapper.toResponseDto(itemRepository.findById(itemPatchDto.getId()).orElseThrow(EntityNotFoundException::new));
    }

    @Transactional
    public String deleteItem(Long itemId) {
        String name = itemRepository.findById(itemId).orElseThrow(EntityNotFoundException::new).getName();
        itemRepository.deleteById(itemId);
        return "삭제한 Item의 name : " + name;
    }
}