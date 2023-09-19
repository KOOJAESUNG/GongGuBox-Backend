package com.gonggubox;

import com.gonggubox.domain.admin.AdminEntity;
import com.gonggubox.domain.member.MemberEntity;
import com.gonggubox.dto.member.MemberDto;
import com.gonggubox.mapper.admin.AdminMapper;
import com.gonggubox.mapper.member.MemberMapper;
import com.gonggubox.repository.admin.AdminRepository;
import com.gonggubox.repository.member.MemberRepository;
import com.gonggubox.service.item.ItemService;
import com.gonggubox.service.member.GroupService;
import com.gonggubox.service.member.MemberService;
import com.gonggubox.service.order.OrderService;
import jakarta.annotation.PostConstruct;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Component
@RequiredArgsConstructor
@Validated
public class Init {

    private final OrderService orderService;

    private final ItemService itemService;

    private final GroupService groupService;

    private final MemberService memberService;
    private final MemberRepository memberRepository;
    private final MemberMapper memberMapper;
    private final AdminMapper adminMapper;
    private final AdminRepository adminRepository;


    @Valid AdminEntity checkValid(@Valid AdminEntity admin) {
        return admin;
    }
    @PostConstruct
    void init() throws Exception {
        MemberDto.MemberPostDto memberPostDto = new MemberDto.MemberPostDto();
        MemberEntity entity = memberMapper.toEntity(memberPostDto);
        entity.setMemberInGroupMemberList();

    }
}
