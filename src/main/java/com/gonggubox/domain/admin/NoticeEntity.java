package com.gonggubox.domain.admin;


import com.gonggubox.constant.NoticeType;
import com.gonggubox.domain.TimeStamp;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * 공지사항 Entity - 서버 점검 등의 공지사항
 */
@Entity
@Getter
@Setter
public class NoticeEntity extends TimeStamp {

    @Id
    @Column(name = "notice_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @NotBlank
    private String title; //공지사항의 제목

    @Lob
    @Column(columnDefinition = "TEXT")
    private String content; //공지사항의 텍스트 부분

    @Enumerated(EnumType.STRING)
    private NoticeType noticeType;//공지사항의 타입. ex) 점검, 사용방법 등

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(
            name = "notice_images",
            joinColumns = @JoinColumn(name = "notice_id")
    )
    @Column(name = "image_url")
    private List<String> imageUrlList = new ArrayList<>(); //공지사항의 이미지 부분

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "admin_id")
    private AdminEntity admin;

}
