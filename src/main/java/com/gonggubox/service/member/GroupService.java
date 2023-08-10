package com.gonggubox.service.member;

import com.gonggubox.dto.member.GroupDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class GroupService {
    @Transactional
    public void createGroup(GroupDto.GroupPostDto GroupPostDto) {

    }

    public void getGroup(Long GroupId) {

    }
    @Transactional
    public void updateGroup(GroupDto.GroupPatchDto GroupPatchDto) {

    }
    @Transactional
    public void deleteGroup(Long GroupId) {

    }
}
