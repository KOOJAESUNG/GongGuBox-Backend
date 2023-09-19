package com.gonggubox.service.member;

import com.gonggubox.domain.member.MemberEntity;
import com.gonggubox.dto.member.MemberDto;
import com.gonggubox.mapper.member.MemberMapper;
import com.gonggubox.repository.member.MemberRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;

    private final MemberMapper memberMapper;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional
    public MemberDto.MemberResponseDto createMember(MemberDto.MemberPostDto memberPostDto) {
        if(!memberRepository.existsByUsername(memberPostDto.getUsername())){
            memberPostDto.setPassword(bCryptPasswordEncoder.encode(memberPostDto.getPassword()));
            MemberEntity member = memberMapper.toEntity(memberPostDto);
            member.setMemberInGroupMemberList();
            return memberMapper.toResponseDto(memberRepository.save(member));
        } else throw new RuntimeException("createMember : 이미 존재하는 username 입니다!!!");
    }

    public MemberDto.MemberResponseDto getMemberById(Long memberId) {
        return memberMapper.toResponseDto(memberRepository.findById(memberId).orElseThrow(EntityNotFoundException::new));
    }

    public MemberDto.MemberResponseDto getMemberByEmail(String email) {
        return memberMapper.toResponseDto(memberRepository.findByEmail(email).orElseThrow(EntityNotFoundException::new));
    }

    @Transactional
    public MemberDto.MemberResponseDto updateMember(Long memberId, MemberDto.MemberPatchDto memberPatchDto) {
        if (memberPatchDto.getPassword() != null)
            memberPatchDto.setPassword(bCryptPasswordEncoder.encode(memberPatchDto.getPassword()));
        memberMapper.updateFromPatchDto(memberPatchDto, memberRepository.findById(memberId).orElseThrow(EntityNotFoundException::new));
        return memberMapper.toResponseDto(memberRepository.findById(memberId).orElseThrow(EntityNotFoundException::new));
    }

    @Transactional
    public String deleteMember(Long memberId) {
        String email = memberRepository.findById(memberId).orElseThrow(EntityNotFoundException::new).getEmail();
        memberRepository.deleteById(memberId);
        return "삭제한 Member의 email : " + email;
    }
}
