package com.gonggubox.dto.admin;


import com.gonggubox.constant.EventType;
import com.gonggubox.domain.admin.AdminEntity;
import lombok.*;

import java.util.List;

public class EventDto {

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class EventPostDto {

        private String content;

        private EventType eventType;

    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class EventResponseDto {
        private Long id;

        private String content;

        private EventType eventType;

        private List<String> imageUrlList;

        private AdminEntity admin;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class EventPatchDto {
        private Long id;

        private String content;

        private EventType eventType;

    }
}
