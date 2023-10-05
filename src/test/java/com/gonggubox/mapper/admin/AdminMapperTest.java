package com.gonggubox.mapper.admin;

import com.gonggubox.domain.admin.AdminEntity;
import com.gonggubox.dto.admin.AdminDto;
import com.gonggubox.repository.admin.AdminRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Transactional
class AdminMapperTest {

    @Autowired
    AdminRepository adminRepository;

    @Autowired
    AdminMapper adminMapper;

    private final List<AdminDto.AdminPostDto> adminPostDtoList = new ArrayList<>();

    @Test
    @DisplayName("toEntity")
    void toEntity() {
        int i = 0;
        for (AdminDto.AdminPostDto adminPostDto : adminPostDtoList) {
            AdminEntity entity = adminMapper.toEntity(adminPostDto);

            Assertions.assertEquals("adminpostdto" + i + "@email.com", entity.getEmail());
            Assertions.assertEquals("adminpostdto!" + i, entity.getPassword());
            Assertions.assertEquals("010-1111-111" + i, entity.getPhoneNumber());
            Assertions.assertEquals("adminpostdto" + i, entity.getUsername());
            i++;
        }
    }

    @Test
    @DisplayName("toResponseDto")
    void toResponseDto() {
        for (int i = 0; i < 5; i++) {
            AdminDto.AdminResponseDto dto = adminMapper.toResponseDto(adminRepository.findByUsername("adminentity" + i));

            Assertions.assertEquals("adminentity" + i + "@email.com",dto.getAdminEmail());
            Assertions.assertEquals("010-1111-112" + i,dto.getAdminPhoneNumber());
            Assertions.assertEquals("adminentity" + i,dto.getAdminUsername());
        }
    }

    @Test
    @DisplayName("updateFromPatchDto")
    void updateFromPatchDto() {
        for (int i = 0; i < 5; i++) {
            AdminDto.AdminPatchDto patch = AdminDto.AdminPatchDto.builder()
                    .adminEmail("adminpatch" + i + "@email.com")
                    .adminPassword("adminpatch!" + i)
                    .adminUsername(null)
                    .adminPhoneNumber("010-1111-113" + i)
                    .build();
            AdminEntity entity = adminRepository.findByUsername("adminentity" + i);
            adminMapper.updateFromPatchDto(patch,entity);

            Assertions.assertEquals("adminpatch" + i + "@email.com",entity.getEmail());
            Assertions.assertEquals("adminpatch!" + i,entity.getPassword());
            Assertions.assertEquals("adminentity" + i,entity.getUsername());
            Assertions.assertEquals("010-1111-113" + i,entity.getPhoneNumber());
        }
    }

    @BeforeEach
    void setUp() {
        for (int i = 0; i < 5; i++) {
            adminPostDtoList.add(
                    AdminDto.AdminPostDto.builder()
                            .adminEmail("adminpostdto" + i + "@email.com")
                            .adminPassword("adminpostdto!" + i)
                            .adminPhoneNumber("010-1111-111" + i)
                            .adminUsername("adminpostdto" + i)
                            .build());


            AdminEntity temp = new AdminEntity();
            temp.setEmail("adminentity" + i + "@email.com");
            temp.setPassword("adminentity!" + i);
            temp.setPhoneNumber("010-1111-112" + i);
            temp.setUsername("adminentity" + i);
            adminRepository.save(temp);

        }

    }
}