package com.gonggubox.controller.item;

import com.gonggubox.dto.item.ItemDto;
import com.gonggubox.service.item.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("item")
public class ItemController {

    private final ItemService itemService;

    @PostMapping("/createItem")
    public ResponseEntity<?> createItem(@RequestBody ItemDto.ItemPostDto itemPostDto) {
        return ResponseEntity.ok().body(itemService.createItem(itemPostDto));
    }

    @GetMapping("/getItem")
    public ResponseEntity<?> getItem(@RequestParam Long itemId) {
        return ResponseEntity.ok().body(itemService.getItem(itemId));
    }

    @PatchMapping("/updateItem")
    public ResponseEntity<?> updateItem(@RequestBody ItemDto.ItemPatchDto itemPatchDto) {
        return ResponseEntity.ok().body(itemService.updateItem(itemPatchDto));
    }

    @DeleteMapping("/deteteItem")
    public ResponseEntity<?> deleteItem(@RequestParam Long itemId) {
        return ResponseEntity.ok().body(itemService.deleteItem(itemId));
    }
}
