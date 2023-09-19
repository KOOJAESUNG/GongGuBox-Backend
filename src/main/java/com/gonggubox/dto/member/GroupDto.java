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
        private String name; //그룹명

        @NotNull
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

        private String groupName;

        private List<ItemDto.ItemResponseDto> itemList;
    }


    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class GroupPatchDto {

        @NotNull
        @Min(1)
        private Long id;

        private String name; //그룹명

        private Address address;
    }
}
