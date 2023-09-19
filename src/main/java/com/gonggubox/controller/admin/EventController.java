package com.gonggubox.controller.admin;

import com.gonggubox.config.spring_security.auth.PrincipalDetails;
import com.gonggubox.dto.admin.EventDto;
import com.gonggubox.service.admin.EventService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("event")
public class EventController {

    private final EventService eventService;

    @PostMapping("/createEvent")
    public ResponseEntity<?> createEvent(@AuthenticationPrincipal PrincipalDetails principalDetails, @RequestBody @Valid EventDto.EventPostDto eventPostDto) {
        return ResponseEntity.ok().body(eventService.createEvent(principalDetails.getUser().getId(), eventPostDto));
    }

    @GetMapping("/getEvent")
    public ResponseEntity<?> getEvent(@RequestParam @NotNull @Min(1) Long eventId) {
        return ResponseEntity.ok().body(eventService.getEvent(eventId));
    }

    @PatchMapping("/updateEvent")
    public ResponseEntity<?> updateEvent(@RequestBody @Valid EventDto.EventPatchDto eventPatchDto) {
        return ResponseEntity.ok().body(eventService.updateEvent(eventPatchDto));
    }

    @DeleteMapping("/deleteEvent")
    public ResponseEntity<?> deleteEvent(@RequestParam @NotNull @Min(1) Long eventId) {
        return ResponseEntity.ok().body(eventService.deleteEvent(eventId));
    }
}
