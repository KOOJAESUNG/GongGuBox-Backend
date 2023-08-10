package com.gonggubox.service.admin;

import com.gonggubox.dto.admin.NoticeDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class NoticeService {
    @Transactional
    public void createNotice(NoticeDto.NoticePostDto NoticePostDto) {

    }

    public void getNotice(Long NoticeId) {

    }
    @Transactional
    public void updateNotice(NoticeDto.NoticePatchDto NoticePatchDto) {

    }
    @Transactional
    public void deleteNotice(Long NoticeId) {

    }
}
