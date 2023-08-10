package com.gonggubox.repository.admin;


import com.gonggubox.domain.admin.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<EventEntity,Long> {
}
