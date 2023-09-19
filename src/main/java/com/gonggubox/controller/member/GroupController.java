package com.gonggubox.controller.member;

import com.gonggubox.dto.member.GroupDto;
import com.gonggubox.service.member.GroupService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("group")
public class GroupController {

    private final GroupService groupService;

    @PostMapping("/createGroup")
    public ResponseEntity<?> createGroup(@RequestBody @Valid GroupDto.GroupPostDto groupPostDto) {
        return ResponseEntity.ok().body(groupService.createGroup(groupPostDto));
    }

    @GetMapping("/getGroup")
    public ResponseEntity<?> getGroup(@RequestParam @NotNull @Min(1) Long groupId) {
        return ResponseEntity.ok().body(groupService.getGroup(groupId));
    }

    @GetMapping("/getGroupItemList")
    public ResponseEntity<?> getGroupItemList(@RequestParam @NotNull @Min(1) Long groupId) {
        return ResponseEntity.ok().body(groupService.getGroupItemList(groupId));
    }

    @PatchMapping("/updateGroup")
    public ResponseEntity<?> updateGroup(@RequestBody @Valid GroupDto.GroupPatchDto groupPatchDto) {
        return ResponseEntity.ok().body(groupService.updateGroup(groupPatchDto));
    }

    @DeleteMapping("/deleteGroup")
    public ResponseEntity<?> deleteGroup(@RequestParam @NotNull @Min(1) Long groupId) {
        return ResponseEntity.ok().body(groupService.deleteGroup(groupId));
    }
}
