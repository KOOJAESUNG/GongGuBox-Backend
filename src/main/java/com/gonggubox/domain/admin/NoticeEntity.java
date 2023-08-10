package com.gonggubox.domain.admin;


import com.gonggubox.constant.NoticeType;
import com.gonggubox.domain.TimeStamp;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class NoticeEntity extends TimeStamp {

    @Id
    @Column(name = "notice_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String content;

    @Enumerated(EnumType.STRING)
    private NoticeType noticeType;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(
            name = "notice_images",
            joinColumns = @JoinColumn(name = "notice_id")
    )
    @Column(name = "image_url")
    private List<String> imageUrlList = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "admin_id")
    private AdminEntity admin;

}
