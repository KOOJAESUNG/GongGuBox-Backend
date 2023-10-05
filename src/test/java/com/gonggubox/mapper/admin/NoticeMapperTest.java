package com.gonggubox.mapper.admin;

import com.gonggubox.constant.NoticeType;
import com.gonggubox.domain.admin.AdminEntity;
import com.gonggubox.domain.admin.NoticeEntity;
import com.gonggubox.dto.admin.NoticeDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Transactional
class NoticeMapperTest {

    @Autowired
    NoticeMapper noticeMapper;

    @Autowired
    AdminMapper adminMapper;

    List<NoticeDto.NoticePostDto> noticePostDtoList = new ArrayList<>();
    List<NoticeEntity> noticeEntityList = new ArrayList<>();

    AdminEntity admin = new AdminEntity();

    @Test
    void toEntity() {
        int i=0;
        for (NoticeDto.NoticePostDto noticePostDto : noticePostDtoList) {
            NoticeEntity entity = noticeMapper.toEntity(noticePostDto, admin);

            Assertions.assertEquals("postdtocontent" + i,entity.getContent());
            Assertions.assertEquals("postdtotitle" + i,entity.getTitle());
            Assertions.assertEquals(NoticeType.NOTI,entity.getNoticeType());
            Assertions.assertEquals(admin,entity.getAdmin());
            i++;
        }
    }

    @Test
    void toResponseDto() {
        for (int i = 0; i < 5; i++) {
            NoticeDto.NoticeResponseDto noticeResponseDto = noticeMapper.toResponseDto(noticeEntityList.get(i));

            Assertions.assertEquals("entitycontent" + i, noticeResponseDto.getNoticeContent());
            Assertions.assertEquals("entitytitle"+i,noticeResponseDto.getNoticeTitle());
            Assertions.assertEquals(NoticeType.NOTI,noticeResponseDto.getNoticeType());
            Assertions.assertEquals(adminMapper.toResponseDto(admin).getAdminEmail(),noticeResponseDto.getNoticeWriterAdminInfo().getAdminEmail());
            Assertions.assertEquals(adminMapper.toResponseDto(admin).getAdminUsername(),noticeResponseDto.getNoticeWriterAdminInfo().getAdminUsername());
            Assertions.assertEquals(adminMapper.toResponseDto(admin).getAdminPhoneNumber(),noticeResponseDto.getNoticeWriterAdminInfo().getAdminPhoneNumber());
        }
    }

    @Test
    void updateFromPatchDto() {
        for (int i = 0; i < 5; i++) {
            NoticeDto.NoticePatchDto patch = NoticeDto.NoticePatchDto.builder()
                    .noticeType(NoticeType.NOTI)
                    .noticeTitle("patchtitle" + i)
                    .noticeContent("patchcontent" + i)
                    .build();

            noticeMapper.updateFromPatchDto(patch, noticeEntityList.get(i));

            Assertions.assertEquals("patchcontent" + i, noticeEntityList.get(i).getContent());
            Assertions.assertEquals("patchtitle"+i, noticeEntityList.get(i).getTitle());
            Assertions.assertEquals(NoticeType.NOTI, noticeEntityList.get(i).getNoticeType());

            patch.setNoticeTitle(null);
            noticeMapper.updateFromPatchDto(patch, noticeEntityList.get(i));
            Assertions.assertEquals("patchtitle"+i, noticeEntityList.get(i).getTitle());
        }
    }

    @BeforeEach
    void setUp() {
        admin.setEmail("adminentity@email.com");
        admin.setPassword("adminentity!");
        admin.setPhoneNumber("010-1111-112");
        admin.setUsername("adminentity");

        for (int i = 0; i < 5; i++) {
            noticePostDtoList.add(
                    NoticeDto.NoticePostDto.builder()
                            .noticeContent("postdtocontent" + i)
                            .noticeTitle("postdtotitle" + i)
                            .noticeType(NoticeType.NOTI)
                            .build()
            );

            NoticeEntity entity = new NoticeEntity();
            entity.setContent("entitycontent"+i);
            entity.setTitle("entitytitle"+i);
            entity.setNoticeType(NoticeType.NOTI);
            entity.setAdmin(admin);
            noticeEntityList.add(entity);
        }

    }
}