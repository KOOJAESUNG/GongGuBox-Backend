package com.gonggubox.domain.admin;

import com.gonggubox.constant.EventType;
import com.gonggubox.domain.TimeStamp;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

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
    private String content;

    @Enumerated(EnumType.STRING)
    private EventType eventType;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(
            name = "event_images",
            joinColumns = @JoinColumn(name = "event_id")
    )
    @Column(name = "image_url")
    private List<String> imageUrlList = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "admin_id")
    private AdminEntity admin;
}
