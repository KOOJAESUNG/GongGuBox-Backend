package com.gonggubox.dto.admin;

import com.gonggubox.constant.NoticeType;
import com.gonggubox.domain.admin.AdminEntity;
import lombok.*;

import java.util.List;

public class NoticeDto {

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class NoticePostDto {

        private String title;

        private String content;

        private NoticeType noticeType;

    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class NoticeResponseDto {
        private Long id;

        private String content;

        private NoticeType noticeType;

        private List<String> imageUrlList;

        private AdminEntity admin;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class NoticePatchDto {
        private Long id;

        private String title;

        private String content;

        private NoticeType noticeType;

    }
}
