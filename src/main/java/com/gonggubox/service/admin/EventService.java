package com.gonggubox.service.admin;

import com.gonggubox.dto.admin.EventDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class EventService {
    @Transactional
    public void createEvent(EventDto.EventPostDto EventPostDto) {

    }

    public void getEvent(Long EventId) {

    }
    @Transactional
    public void updateEvent(EventDto.EventPatchDto EventPatchDto) {

    }
    @Transactional
    public void deleteEvent(Long EventId) {

    }
}
