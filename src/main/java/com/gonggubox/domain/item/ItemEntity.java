package com.gonggubox.domain.item;


import com.gonggubox.constant.Address;
import com.gonggubox.constant.ItemStatus;
import com.gonggubox.domain.TimeStamp;
import com.gonggubox.domain.member.GroupEntity;
import com.gonggubox.domain.order.OrderItemEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class ItemEntity extends TimeStamp {

    @Id
    @Column(name = "item_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, length = 50)
    private String name; //상품명

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(
            name = "item_images",
            joinColumns = @JoinColumn(name = "item_id")
    )
    @Column(name = "image_url")
    private List<String> imageList = new ArrayList<>(); //상품 이미지

    @Column(nullable = false)
    private Long price; //가격

    private Integer count; //수량. if 1+2 --> 3

    private String itemLink; //상품 링크

    private Address address; // 약속장소

    @Lob
    @Column(columnDefinition = "TEXT")
    private String content; //설명

    @Enumerated(EnumType.STRING)
    private ItemStatus itemStatus = ItemStatus.GATHER; //상태


    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    private List<OrderItemEntity> orderItem; //개수를 충족했는지 확인하기 위해 필요

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id")
    private GroupEntity group;

//    @OneToMany(mappedBy = "item")
//    private List<CategoryItemEntity> categoryItemList = new ArrayList<>();

}
