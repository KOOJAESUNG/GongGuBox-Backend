package com.gonggubox.service.admin;

import com.gonggubox.domain.admin.EventEntity;
import com.gonggubox.dto.admin.EventDto;
import com.gonggubox.mapper.admin.EventMapper;
import com.gonggubox.repository.admin.AdminRepository;
import com.gonggubox.repository.admin.EventRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class EventService {

    private final EventRepository eventRepository;

    private final EventMapper eventMapper;

    private final AdminRepository adminRepository;

    @Transactional
    public EventDto.EventResponseDto createEvent(Long adminId, EventDto.EventPostDto eventPostDto) {
        return eventMapper.toResponseDto(eventRepository.save(
                eventMapper.toEntity(eventPostDto,adminRepository.findById(adminId).orElseThrow(EntityNotFoundException::new))
        ));
    }

    public EventDto.EventResponseDto getEvent(Long eventId) {
        return eventMapper.toResponseDto(eventRepository.findById(eventId).orElseThrow(EntityNotFoundException::new));
    }

    @Transactional
    public EventDto.EventResponseDto updateEvent(EventDto.EventPatchDto eventPatchDto) {
        EventEntity eventEntity = eventRepository.findById(eventPatchDto.getId()).orElseThrow(EntityNotFoundException::new);
        eventMapper.updateFromPatchDto(eventPatchDto, eventEntity);
        return eventMapper.toResponseDto(eventRepository.findById(eventPatchDto.getId()).orElseThrow(EntityNotFoundException::new));
    }

    @Transactional
    public String deleteEvent(Long eventId) {
        String title = eventRepository.findById(eventId).orElseThrow(EntityNotFoundException::new).getTitle();
        eventRepository.deleteById(eventId);
        return "삭제한 event의 title : "+title;
    }
}
