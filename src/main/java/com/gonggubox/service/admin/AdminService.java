package com.gonggubox.service.admin;

import com.gonggubox.domain.admin.AdminEntity;
import com.gonggubox.dto.admin.AdminDto;
import com.gonggubox.mapper.admin.AdminMapper;
import com.gonggubox.repository.admin.AdminRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class AdminService {

    @Value("${spring.security.oauth2.client.registration.kakao.client-secret}")
    private String test;

    private final AdminRepository adminRepository;

    private final AdminMapper adminMapper;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional
    public AdminDto.AdminResponseDto createAdmin(AdminDto.AdminPostDto adminPostDto) {
        System.out.println(test);
        if(!adminRepository.existsByUsername(adminPostDto.getUsername())){
            adminPostDto.setPassword(bCryptPasswordEncoder.encode(adminPostDto.getPassword()));
            return adminMapper.toResponseDto(adminRepository.save(adminMapper.toEntity(adminPostDto)));
        }
        else throw new RuntimeException("createAdmin : 이미 존재하는 username 입니다!!!");
    }

    public AdminDto.AdminResponseDto getAdmin(Long adminId) {
        return adminMapper.toResponseDto(adminRepository.findById(adminId).orElseThrow(EntityNotFoundException::new));
    }
    @Transactional
    public AdminDto.AdminResponseDto updateAdmin(Long adminId, AdminDto.AdminPatchDto adminPatchDto) {
        AdminEntity adminEntity = adminRepository.findById(adminId).orElseThrow(EntityNotFoundException::new);
        adminMapper.updateFromPatchDto(adminPatchDto,adminEntity);
        return adminMapper.toResponseDto(adminRepository.findById(adminId).orElseThrow(EntityNotFoundException::new));
    }
    @Transactional
    public String deleteAdmin(Long adminId) {
        String username = adminRepository.findById(adminId).orElseThrow(EntityNotFoundException::new).getUsername();
        adminRepository.deleteById(adminId);
        return "삭제한 admin의 username : "+username;
    }
}
