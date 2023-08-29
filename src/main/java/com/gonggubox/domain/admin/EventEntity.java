package com.gonggubox.domain.admin;

import com.gonggubox.constant.EventType;
import com.gonggubox.domain.TimeStamp;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


/**
 * 이벤트 Entity - 상품 할인 등의 이벤트.
 */
@Entity
@Getter
@Setter
public class EventEntity extends TimeStamp {

    @Id
    @Column(name = "event_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String content; //이벤트의 텍스트 부분

    @Enumerated(EnumType.STRING)
    private EventType eventType; //이벤트의 타입. ex) 경품추첨, 할인 등

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(
            name = "event_images",
            joinColumns = @JoinColumn(name = "event_id")
    )
    @Column(name = "image_url")
    private List<String> imageUrlList = new ArrayList<>(); // 이벤트의 이미지

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "admin_id")
    private AdminEntity admin;
}
