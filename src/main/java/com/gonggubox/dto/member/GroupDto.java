package com.gonggubox.dto.member;

import com.gonggubox.constant.Address;
import com.gonggubox.domain.item.ItemEntity;
import lombok.*;

import java.util.List;

public class GroupDto {

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class GroupPostDto {

        private String name; //그룹명

        private Address address;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class GroupResponseDto {
        private Long id;

        private String name; //그룹명

        private Address address;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class GroupItemResponseDto {
        private Long groupId;

        private List<ItemEntity> itemList;
    }


    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class GroupPatchDto {
        private Long id;

        private String name; //그룹명

        private Address address;
    }
}
