package com.gonggubox.controller.admin;

import com.gonggubox.config.spring_security.auth.PrincipalDetails;
import com.gonggubox.dto.admin.AdminDto;
import com.gonggubox.service.admin.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("admin")
public class AdminController {

    private final AdminService adminService;

    @PostMapping("/createAdmin")
    public ResponseEntity<?> createAdmin(@RequestBody AdminDto.AdminPostDto adminPostDto) {
        return ResponseEntity.ok().body(adminService.createAdmin(adminPostDto));
    }

    @GetMapping("/getAdmin")
    public ResponseEntity<?> getAdmin(@AuthenticationPrincipal PrincipalDetails principalDetails) {
        return ResponseEntity.ok().body(adminService.getAdmin(principalDetails.getUser().getId()));
    }

    @PatchMapping("/updateAdmin")
    public ResponseEntity<?> updateAdmin(@AuthenticationPrincipal PrincipalDetails principalDetails, @RequestBody AdminDto.AdminPatchDto adminPatchDto) {
        return ResponseEntity.ok().body(adminService.updateAdmin(principalDetails.getUser().getId(), adminPatchDto));
    }

    @DeleteMapping("/deleteAdmin")
    public ResponseEntity<?> deleteAdmin(@AuthenticationPrincipal PrincipalDetails principalDetails) {
        return ResponseEntity.ok().body(adminService.deleteAdmin(principalDetails.getUser().getId()));
    }
}
