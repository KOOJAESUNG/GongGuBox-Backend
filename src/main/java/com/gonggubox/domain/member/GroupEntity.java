package com.gonggubox.domain.member;

import com.gonggubox.constant.Address;
import com.gonggubox.domain.item.ItemEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

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
    private Address address;

    @OneToMany(mappedBy = "group")
    private List<ItemEntity> itemList;

}
