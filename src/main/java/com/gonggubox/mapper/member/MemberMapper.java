package com.gonggubox.mapper.member;

import com.gonggubox.domain.member.GroupMemberEntity;
import com.gonggubox.domain.member.MemberEntity;
import com.gonggubox.dto.member.GroupDto;
import com.gonggubox.dto.member.MemberDto;
import com.gonggubox.repository.member.GroupRepository;
import jakarta.persistence.EntityNotFoundException;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Mapper(
        componentModel = "spring", // 빌드 시 구현체 만들고 빈으로 등록
        injectionStrategy = InjectionStrategy.CONSTRUCTOR, // 생성자 주입 전략
        unmappedTargetPolicy = ReportingPolicy.ERROR // 일치하지 않는 필드가 있으면 빌드 시 에러
)
public abstract class MemberMapper {

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private GroupMapper groupMapper;

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "createAt", ignore = true),
            @Mapping(target = "modifiedAt", ignore = true),
            @Mapping(target = "memberClass", ignore = true),
            @Mapping(target = "cart", ignore = true),
            @Mapping(target = "orderList", ignore = true),
            @Mapping(source = "groupIdList", target = "groupMemberList", qualifiedByName = "groupIdListToGroupMemberEntityList")
    })
    public abstract MemberEntity toEntity(MemberDto.MemberPostDto MemberPostDto);

    @Named("groupIdListToGroupMemberEntityList")
    List<GroupMemberEntity> groupIdListToGroupMemberEntityList(List<Long> groupIdList) {
        if (groupIdList == null) return null;
        List<GroupMemberEntity> temp = new ArrayList<>();
        groupIdList.forEach(o -> {
            GroupMemberEntity groupMemberEntity = new GroupMemberEntity();
            groupMemberEntity.setGroup(groupRepository.findById(o).orElseThrow(EntityNotFoundException::new));
            temp.add(groupMemberEntity);
        });
        return temp;
    }

    @Mapping(source = "groupMemberList", target = "groupList", qualifiedByName = "groupMemberListToGroupResponseDtoList")
    public abstract MemberDto.MemberResponseDto toResponseDto(MemberEntity MemberEntity);

    @Named("groupMemberListToGroupResponseDtoList")
    List<GroupDto.GroupResponseDto> groupMemberListToGroupResponseDtoList(List<GroupMemberEntity> groupMemberList) {
        if (groupMemberList == null) return null;
        List<GroupDto.GroupResponseDto> temp = new ArrayList<>();
        groupMemberList.forEach(o ->
                temp.add(
                        groupMapper.toResponseDto(o.getGroup())
                )
        );
        return temp;
    }

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "createAt", ignore = true),
            @Mapping(target = "modifiedAt", ignore = true),
            @Mapping(target = "cart", ignore = true),
            @Mapping(target = "orderList", ignore = true),
            @Mapping(source = "groupIdList", target = "groupMemberList", qualifiedByName = "groupIdListToGroupMemberEntityList")
    })
    public abstract void updateFromPatchDto(MemberDto.MemberPatchDto MemberPatchDto, @MappingTarget MemberEntity MemberEntity);

}
