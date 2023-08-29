package com.gonggubox.domain.member;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * 그룹과 회원간의 다대다 관계를 풀어내기 위한 Entity
 */
@Entity
@Getter
@Setter
public class GroupMemberEntity {

    @Id
    @Column(name = "group_member_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id")
    private GroupEntity group;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberEntity member;

}
