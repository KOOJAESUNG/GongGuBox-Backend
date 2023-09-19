package com.gonggubox.domain.admin;


import com.gonggubox.constant.Role;
import com.gonggubox.domain.TimeStamp;
import com.gonggubox.domain.UserEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
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

    @NotNull
    @NotBlank
    private String password;

    @NotNull
    @NotBlank
    @Column(unique = true)
    private String username; //로그인 아이디

    @NotNull
    @NotBlank
    @Email
    @Column(unique = true)
    private String email;

    @NotNull
    @NotBlank
    @Pattern(regexp = "^01([0|1|6|7|8|9]?)-?([0-9]{3,4})-?([0-9]{4})$",
            message = "휴대폰 번호를 정확하게 입력해주세요.")
    @Column(unique = true)
    private String phoneNumber;

}
