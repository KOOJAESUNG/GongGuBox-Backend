package com.gonggubox.controller.admin;

import com.gonggubox.config.spring_security.auth.PrincipalDetails;
import com.gonggubox.dto.admin.NoticeDto;
import com.gonggubox.service.admin.NoticeService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("notice")
public class NoticeController {

    private final NoticeService noticeService;

    @PostMapping("/createNotice")
    public ResponseEntity<?> createNotice(@AuthenticationPrincipal PrincipalDetails principalDetails, @RequestBody @Valid NoticeDto.NoticePostDto noticePostDto) {
        return ResponseEntity.ok().body(noticeService.createNotice(principalDetails.getUser().getId(), noticePostDto));
    }

    @GetMapping("/getNotice")
    public ResponseEntity<?> getNotice(@RequestParam @NotNull @Min(1) Long noticeId) {
        return ResponseEntity.ok().body(noticeService.getNotice(noticeId));
    }

    @PatchMapping("/updateNotice")
    public ResponseEntity<?> updateNotice(@RequestBody @Valid NoticeDto.NoticePatchDto noticePatchDto) {
        return ResponseEntity.ok().body(noticeService.updateNotice(noticePatchDto));
    }

    @DeleteMapping("/deleteNotice")
    public ResponseEntity<?> deleteNotice(@RequestParam @NotNull @Min(1) Long noticeId) {
        return ResponseEntity.ok().body(noticeService.deleteNotice(noticeId));
    }
}
