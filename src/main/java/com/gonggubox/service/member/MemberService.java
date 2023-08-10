package com.gonggubox.service.member;

import com.gonggubox.dto.member.MemberDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class MemberService {
    @Transactional
    public void createMember(MemberDto.MemberPostDto MemberPostDto) {

    }

    public void getMember(Long MemberId) {

    }
    @Transactional
    public void updateMember(MemberDto.MemberPatchDto MemberPatchDto) {

    }
    @Transactional
    public void deleteMember(Long MemberId) {

    }
}
