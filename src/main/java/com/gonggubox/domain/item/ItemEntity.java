package com.gonggubox.domain.item;


import com.gonggubox.constant.Address;
import com.gonggubox.constant.ItemStatus;
import com.gonggubox.domain.TimeStamp;
import com.gonggubox.domain.member.GroupEntity;
import com.gonggubox.domain.order.OrderItemEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * 상품 Entity - 상품의 정보를 저장하고 있다.
 */
@Entity
@Getter
@Setter
public class ItemEntity extends TimeStamp {

    @Id
    @Column(name = "item_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @NotBlank
    @Size(max = 50)
    private String name; //상품명

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(
            name = "item_images",
            joinColumns = @JoinColumn(name = "item_id")
    )
    @Column(name = "image_url")
    private List<String> imageList = new ArrayList<>(); //상품 이미지

    @NotNull
    @Min(1)
    private Long price; //가격

    @NotNull
    @Min(1)
    private Integer count; //수량. if 1+2 --> 3

    private String itemLink; //상품 링크

    private Address address; // 약속장소

    @Lob
    @Column(columnDefinition = "TEXT")
    private String content; //설명

    @Enumerated(EnumType.STRING)
    private ItemStatus itemStatus = ItemStatus.GATHER; //상태


    @OneToMany(mappedBy = "item")
    private List<OrderItemEntity> orderItem = new ArrayList<>(); //개수를 충족했는지 확인하기 위해 필요

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id")
    private GroupEntity group; //당근마켓처럼 상품은 각 그룹에 귀속된다.

//    @OneToMany(mappedBy = "item")
//    private List<CategoryItemEntity> categoryItemList = new ArrayList<>();

}
