package com.gonggubox.domain.member;

import com.gonggubox.constant.Address;
import com.gonggubox.domain.item.ItemEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * 그룹 Entity - 대학교, 직장과 같은 그룹
 */
@Entity
@Getter
@Setter
public class GroupEntity {

    @Id
    @Column(name = "group_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name; //그룹명

    @Embedded
    private Address address; //그룹의 대표 주소

    @OneToMany(mappedBy = "group", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<ItemEntity> itemList = new ArrayList<>(); //Group이 삭제되면 Group에 속한 Item도 같이 삭제됨!!

}
