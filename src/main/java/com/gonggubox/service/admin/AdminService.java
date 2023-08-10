package com.gonggubox.service.admin;

import com.gonggubox.dto.admin.AdminDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class AdminService {
    @Transactional
    public void createAdmin(AdminDto.AdminPostDto adminPostDto) {

    }

    public void getAdmin(Long adminId) {

    }
    @Transactional
    public void updateAdmin(AdminDto.AdminPatchDto adminPatchDto) {

    }
    @Transactional
    public void deleteAdmin(Long adminId) {

    }
}
