package com.gonggubox.mapper.admin;

import com.gonggubox.constant.EventType;
import com.gonggubox.domain.admin.AdminEntity;
import com.gonggubox.domain.admin.EventEntity;
import com.gonggubox.dto.admin.EventDto;
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
class EventMapperTest {

    @Autowired
    EventMapper eventMapper;

    @Autowired
    AdminMapper adminMapper;

    List<EventDto.EventPostDto> eventPostDtoList = new ArrayList<>();
    List<EventEntity> eventEntityList = new ArrayList<>();

    AdminEntity admin = new AdminEntity();

    @Test
    void toEntity() {
        int i=0;
        for (EventDto.EventPostDto eventPostDto : eventPostDtoList) {
            EventEntity entity = eventMapper.toEntity(eventPostDto, admin);

            Assertions.assertEquals("postdtocontent" + i,entity.getContent());
            Assertions.assertEquals("postdtotitle" + i,entity.getTitle());
            Assertions.assertEquals(EventType.ETYPE,entity.getEventType());
            Assertions.assertEquals(admin,entity.getAdmin());
            i++;
        }
    }

    @Test
    void toResponseDto() {
        for (int i = 0; i < 5; i++) {
            EventDto.EventResponseDto eventResponseDto = eventMapper.toResponseDto(eventEntityList.get(i));

            Assertions.assertEquals("entitycontent" + i, eventResponseDto.getEventContent());
            Assertions.assertEquals("entitytitle"+i,eventResponseDto.getEventTitle());
            Assertions.assertEquals(EventType.ETYPE,eventResponseDto.getEventType());
            Assertions.assertEquals(adminMapper.toResponseDto(admin).getAdminEmail(),eventResponseDto.getEventWriterAdminInfo().getAdminEmail());
            Assertions.assertEquals(adminMapper.toResponseDto(admin).getAdminUsername(),eventResponseDto.getEventWriterAdminInfo().getAdminUsername());
            Assertions.assertEquals(adminMapper.toResponseDto(admin).getAdminPhoneNumber(),eventResponseDto.getEventWriterAdminInfo().getAdminPhoneNumber());
        }
    }

    @Test
    void updateFromPatchDto() {
        for (int i = 0; i < 5; i++) {
            EventDto.EventPatchDto patch = EventDto.EventPatchDto.builder()
                    .eventType(EventType.ETYPE)
                    .eventTitle("patchtitle" + i)
                    .eventContent("patchcontent" + i)
                    .build();

            eventMapper.updateFromPatchDto(patch,eventEntityList.get(i));

            Assertions.assertEquals("patchcontent" + i, eventEntityList.get(i).getContent());
            Assertions.assertEquals("patchtitle"+i,eventEntityList.get(i).getTitle());
            Assertions.assertEquals(EventType.ETYPE,eventEntityList.get(i).getEventType());

            patch.setEventTitle(null);
            eventMapper.updateFromPatchDto(patch,eventEntityList.get(i));
            Assertions.assertEquals("patchtitle"+i,eventEntityList.get(i).getTitle());
        }
    }

    @BeforeEach
    void setUp() {
        admin.setEmail("adminentity@email.com");
        admin.setPassword("adminentity!");
        admin.setPhoneNumber("010-1111-112");
        admin.setUsername("adminentity");

        for (int i = 0; i < 5; i++) {
            eventPostDtoList.add(
                    EventDto.EventPostDto.builder()
                            .eventContent("postdtocontent" + i)
                            .eventTitle("postdtotitle" + i)
                            .eventType(EventType.ETYPE)
                            .build()
            );

            EventEntity entity = new EventEntity();
            entity.setContent("entitycontent"+i);
            entity.setTitle("entitytitle"+i);
            entity.setEventType(EventType.ETYPE);
            entity.setAdmin(admin);
            eventEntityList.add(entity);
        }

    }
}