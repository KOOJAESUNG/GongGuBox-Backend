package com.gonggubox.service.admin;

import com.gonggubox.domain.admin.AdminEntity;
import com.gonggubox.dto.admin.AdminDto;
import com.gonggubox.mapper.admin.AdminMapper;
import com.gonggubox.repository.admin.AdminRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class AdminService {

    private final AdminRepository adminRepository;

    private final AdminMapper adminMapper;

    @Transactional
    public AdminEntity createAdmin(AdminDto.AdminPostDto adminPostDto) {
        if(!adminRepository.existsByUsername(adminPostDto.getUsername()))
            return adminRepository.save(adminMapper.toEntity(adminPostDto));
        else throw new RuntimeException("createAdmin : 이미 존재하는 username 입니다!!!");
    }

    public AdminEntity getAdmin(Long adminId) {
        return adminRepository.findById(adminId).orElseThrow(EntityNotFoundException::new);
    }
    @Transactional
    public AdminEntity updateAdmin(Long adminId, AdminDto.AdminPatchDto adminPatchDto) {
        AdminEntity adminEntity = adminRepository.findById(adminId).orElseThrow(EntityNotFoundException::new);
        adminMapper.updateFromPatchDto(adminPatchDto,adminEntity);
        return adminRepository.findById(adminId).orElseThrow(EntityNotFoundException::new);
    }
    @Transactional
    public String deleteAdmin(Long adminId) {
        String username = adminRepository.findById(adminId).orElseThrow(EntityNotFoundException::new).getUsername();
        adminRepository.deleteById(adminId);
        return "삭제한 admin의 username : "+username;
    }
}
