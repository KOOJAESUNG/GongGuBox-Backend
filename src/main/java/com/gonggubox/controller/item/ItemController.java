package com.gonggubox.controller.item;

import com.gonggubox.dto.item.ItemDto;
import com.gonggubox.service.item.ItemService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("item")
public class ItemController {

    private final ItemService itemService;

    @PostMapping("/createItem")
    public ResponseEntity<?> createItem(@RequestBody @Valid ItemDto.ItemPostDto itemPostDto) {
        return ResponseEntity.ok().body(itemService.createItem(itemPostDto));
    }

    @GetMapping("/getItem")
    public ResponseEntity<?> getItem(@RequestParam @NotNull @Min(1) Long itemId) {
        return ResponseEntity.ok().body(itemService.getItem(itemId));
    }

    @PatchMapping("/updateItem")
    public ResponseEntity<?> updateItem(@RequestBody @Valid ItemDto.ItemPatchDto itemPatchDto) {
        return ResponseEntity.ok().body(itemService.updateItem(itemPatchDto));
    }

    @DeleteMapping("/deteteItem")
    public ResponseEntity<?> deleteItem(@RequestParam @NotNull @Min(1) Long itemId) {
        return ResponseEntity.ok().body(itemService.deleteItem(itemId));
    }
}
