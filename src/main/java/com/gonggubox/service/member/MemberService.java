package com.gonggubox.service.member;

import com.gonggubox.constant.ErrorCode;
import com.gonggubox.domain.member.MemberEntity;
import com.gonggubox.dto.member.MemberDto;
import com.gonggubox.exception.AppException;
import com.gonggubox.mapper.member.MemberMapper;
import com.gonggubox.repository.member.MemberRepository;
import com.gonggubox.utils.JwtTokenUtil;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;

    private final MemberMapper memberMapper;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Value("${jwt.toekn.secret")
    private String key;
    private Long expireTimeMs = 1000 * 60 * 60l; //1시간

    @Transactional
    public MemberDto.MemberResponseDto join(MemberDto.MemberJoinDto memberJoinDto) {
        //username 중복 check
        memberRepository.findByUsername(memberJoinDto.getUsername())
                .ifPresent(user -> {
                    throw new AppException(ErrorCode.USERNAME_DUPLICATED, memberJoinDto.getUsername() + "는 이미 있습니다.");
                });

        //인코딩해서 저장
        if(memberJoinDto.getPassword()!=null) memberJoinDto.setPassword(bCryptPasswordEncoder.encode(memberJoinDto.getPassword()));


        // RequestDto -> Entity
        MemberEntity memberEntity = memberMapper.toJoinEntity(memberJoinDto);

        // DB에 Entity 저장
        MemberEntity savedMember = memberRepository.save(memberEntity);

        // Entity -> ResponseDto
        MemberDto.MemberResponseDto responseDto = memberMapper.toResponseDto(savedMember);

        return responseDto;
    }

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
