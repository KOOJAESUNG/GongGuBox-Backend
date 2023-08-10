package com.gonggubox.repository.admin;


import com.gonggubox.domain.admin.AdminEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<AdminEntity,Long> {
}
