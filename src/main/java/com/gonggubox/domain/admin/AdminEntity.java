package com.gonggubox.domain.admin;


import com.gonggubox.constant.Role;
import com.gonggubox.domain.TimeStamp;
import com.gonggubox.domain.UserEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


/**
 * 운영자 Entity - 이벤트, 공지사항, 회원 관리, 상품 관리 등의 권한을 가진다.
 */
@Entity
@Getter
@Setter
public class AdminEntity extends TimeStamp implements UserEntity {

    @Id
    @Column(name = "admin_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @Enumerated(EnumType.STRING)
    private final Role role = Role.ADMIN;

    private String password;

    private String username; //로그인 아이디

    private String email;

    private String phoneNumber;

//    @OneToMany(mappedBy = "admin")
//    private List<NoticeEntity> noticeList = new ArrayList<>();
//
//    @OneToMany(mappedBy = "admin")
//    private List<EventEntity> eventList = new ArrayList<>();


}
