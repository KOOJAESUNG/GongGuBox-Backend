package com.gonggubox.service.member;

import com.gonggubox.dto.member.GroupDto;
import com.gonggubox.mapper.member.GroupMapper;
import com.gonggubox.repository.member.GroupRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class GroupService {

    private final GroupRepository groupRepository;

    private final GroupMapper groupMapper;

    @Transactional
    public GroupDto.GroupResponseDto createGroup(GroupDto.GroupPostDto groupPostDto) {
        return groupMapper.toResponseDto(groupRepository.save(groupMapper.toEntity(groupPostDto)));
    }

    public GroupDto.GroupResponseDto getGroup(Long groupId) {
        return groupMapper.toResponseDto(groupRepository.findById(groupId).orElseThrow(EntityNotFoundException::new));
    }

    public GroupDto.GroupItemResponseDto getGroupItemList(Long groupId) {
        return groupMapper.toGroupItemResponseDto(groupRepository.findById(groupId).orElseThrow(EntityNotFoundException::new));
    }

    @Transactional
    public GroupDto.GroupResponseDto updateGroup(GroupDto.GroupPatchDto groupPatchDto) {
        groupMapper.updateFromPatchDto(groupPatchDto, groupRepository.findById(groupPatchDto.getId()).orElseThrow(EntityNotFoundException::new));
        return groupMapper.toResponseDto(groupRepository.findById(groupPatchDto.getId()).orElseThrow(EntityNotFoundException::new));
    }

    @Transactional
    public String deleteGroup(Long groupId) {
        String name = groupRepository.findById(groupId).orElseThrow(EntityNotFoundException::new).getName();
        groupRepository.deleteById(groupId);
        return "삭제한 Group의 name : " + name;
    }

//    public ItemDto.ItemResponseDto getAllItemInGroup(Long groupId) {
//
//    }
}
