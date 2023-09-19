package com.gonggubox.controller.member;

import com.gonggubox.config.spring_security.auth.PrincipalDetails;
import com.gonggubox.dto.member.MemberDto;
import com.gonggubox.service.member.MemberService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("member")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/createMember")
    public ResponseEntity<?> createMember(@RequestBody @Valid MemberDto.MemberPostDto memberPostDto) {
        return ResponseEntity.ok().body(memberService.createMember(memberPostDto));
    }

    @GetMapping("/getMemberById")
    public ResponseEntity<?> getMemberById(@AuthenticationPrincipal PrincipalDetails principalDetails) {
        return ResponseEntity.ok().body(memberService.getMemberById(principalDetails.getUser().getId()));
    }

    @GetMapping("/getMemberByEmail")
    public ResponseEntity<?> getMemberByEmail(@RequestParam @NotBlank String email) {
        return ResponseEntity.ok().body(memberService.getMemberByEmail(email));
    }

    @PatchMapping("/updateMember")
    public ResponseEntity<?> updateMember(@AuthenticationPrincipal PrincipalDetails principalDetails, @RequestBody @Valid MemberDto.MemberPatchDto memberPatchDto) {
        return ResponseEntity.ok().body(memberService.updateMember(principalDetails.getUser().getId(), memberPatchDto));
    }

    @DeleteMapping("/deleteMember")
    public ResponseEntity<?> deleteMember(@AuthenticationPrincipal PrincipalDetails principalDetails) {
        return ResponseEntity.ok().body(memberService.deleteMember(principalDetails.getUser().getId()));
    }

}
