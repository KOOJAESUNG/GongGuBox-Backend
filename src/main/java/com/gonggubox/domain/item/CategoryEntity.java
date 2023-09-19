package com.gonggubox.domain.item;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * 카테고리 Entity - 상품은 각 카테고리에 소속된다
 */
@Entity
@Getter
@Setter
public class CategoryEntity {

    @Id
    @Column(name = "category_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @NotBlank
    private String name; //카테고리의 이름 ex) 생필품, 전자기기 등

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private CategoryEntity parent; //이 카테고리의 상위 카테고리.

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CategoryEntity> child = new ArrayList<>(); //이 카테고리의 하위 카테고리.

}
