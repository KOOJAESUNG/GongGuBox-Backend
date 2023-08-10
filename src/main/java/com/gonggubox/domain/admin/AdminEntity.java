package com.gonggubox.domain.admin;


import com.gonggubox.constant.Role;
import com.gonggubox.domain.TimeStamp;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class AdminEntity extends TimeStamp {

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
