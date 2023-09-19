package com.gonggubox.repository.member;


import com.gonggubox.domain.member.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<MemberEntity, Long> {

    MemberEntity findByUsername(String username);

     Optional<MemberEntity> findByEmail(String email);

    boolean existsByUsername(String username);
}
