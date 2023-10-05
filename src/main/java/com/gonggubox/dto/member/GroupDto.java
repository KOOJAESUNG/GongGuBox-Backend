package com.gonggubox.dto.member;

import com.gonggubox.constant.Address;
import com.gonggubox.dto.item.ItemDto;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

public class GroupDto {

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class GroupPostDto {

        @NotBlank
        private String groupName; //그룹명

        @NotNull
        private Address groupAddress;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class GroupResponseDto {
        private Long groupId;

        private String groupName; //그룹명

        private Address groupAddress;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class GroupItemResponseDto {
        private Long groupId;

        private String groupName;

        private List<ItemDto.ItemResponseDto> groupItemList;
    }


    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class GroupPatchDto {

        @NotNull
        @Min(1)
        private Long groupId;

        private String groupName; //그룹명

        private Address groupAddress;
    }
}
