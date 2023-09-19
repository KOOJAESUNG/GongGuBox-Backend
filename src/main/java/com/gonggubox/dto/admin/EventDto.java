package com.gonggubox.dto.admin;


import com.gonggubox.constant.EventType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

public class EventDto {

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class EventPostDto {

        @NotBlank
        private String title;

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

        private AdminDto.AdminResponseDto admin;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class EventPatchDto {

        @NotNull
        @Min(1)
        private Long id;

        private String title;

        private String content;

        private EventType eventType;

    }
}
