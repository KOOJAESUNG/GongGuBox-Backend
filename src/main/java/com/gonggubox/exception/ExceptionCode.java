package com.gonggubox.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

@Getter
@AllArgsConstructor
public enum ExceptionCode {


    /* 400 BAD_REQUEST : 잘못된 요청 */
    INVALID_REFRESH_TOKEN(BAD_REQUEST, "리프레시 토큰이 유효하지 않습니다"),
    MISMATCH_REFRESH_TOKEN(BAD_REQUEST, "리프레시 토큰의 유저 정보가 일치하지 않습니다"),
    CREATE_FAIL_MEMBERSCORENOTE(BAD_REQUEST, "멤버 점수 내역 생성에 실패하였습니다"),
    CREATE_FAIL_TEAMSCORENOTE(BAD_REQUEST, "팀 점수 내역 생성에 실패하였습니다"),
    CREATE_FAIL_TEAM(BAD_REQUEST, "팀 생성에 실패하였습니다"),

    /* 401 UNAUTHORIZED : 인증되지 않은 사용자 */
    INVALID_AUTH_TOKEN(UNAUTHORIZED, "권한 정보가 없는 토큰입니다"),
    UNAUTHORIZED_MEMBER(UNAUTHORIZED, "현재 내 계정 정보가 존재하지 않습니다"),
    PHONE_AUTHENTICATION_NEED(UNAUTHORIZED, "핸드폰 인증을 다시 시도해 주세요"),

    /* 404 NOT_FOUND : Resource 를 찾을 수 없음 */
    MEMBER_NOT_FOUND(NOT_FOUND, "해당 유저 정보를 찾을 수 없습니다"),
    ADMIN_NOT_FOUND(NOT_FOUND, "해당 어드민 정보를 찾을 수 업습니다"),
    TEAM_NOT_FOUND(NOT_FOUND, "해당 팀 정보를 찾을 수 없습니다"),
    REFRESH_TOKEN_NOT_FOUND(NOT_FOUND, "로그아웃 된 사용자입니다"),
    FILE_IS_NOT_EXIST(NOT_FOUND, "해당 파일이 존재하지 않습니다"),
    MEMBERSCORE_IS_NOT_EXIST(NOT_FOUND, "개인점수가 존재하지 않습니다"),
    MEMBERSCORENOTE_IS_NOT_EXIST(NOT_FOUND, "개인점수 내역이 존재하지 않습니다"),
    TEAMSCORE_IS_NOT_EXIST(NOT_FOUND, "팀 점수가 존재하지 않습니다"),
    TEAMSCORENOTE_IS_NOT_EXIST(NOT_FOUND, "팀 점수 내역이 존재하지 않습니다."),
    SCHEDULE_IS_NOT_EXIST(NOT_FOUND, "일정이 존재하지 않습니다"),
    NOTICE_IS_NOT_EXIST(NOT_FOUND, "공지사항이 존재하지 않습니다"),
    CERTIFICATIONNUMBER_IS_NOT_EXIST(NOT_FOUND, "인증번호를 다시 요청해주세요"),


    /* 409 CONFLICT : Resource 의 현재 상태와 충돌. 보통 중복된 데이터 존재 */
    UPDATE_FAIL_ADMIN(CONFLICT, "어드민 업데이트에 실패하였습니다"),
    UPDATE_FAIL_MEMBERSCORENOTE(CONFLICT, "멤버 점수 내역 업데이트에 실패하였습니다."),
    UPDATE_FAIL_NOTICE(CONFLICT, "공지사항 업데이트에 실패하였습니다"),
    UPDATE_FAIL_NOTICEIMAGE(CONFLICT, "공지사항 이미지 업데이트에 실패하였습니다"),
    UPDATE_FAIL_MEMBERIMAGE(CONFLICT, "멤 이미지 업데이트에 실패하였습니다"),
    UPDATE_FAIL_SCHEDULE(CONFLICT, "스케줄 업데이트에 실패하였습니다"),
    UPDATE_FAIL_TEAMSCORENOTE(CONFLICT, "팀 점수 내역 업데이트에 실패하였습니다"),
    UPDATE_FAIL_MEMBER(CONFLICT, "멤버 업데이트에 실패하였습니다"),
    UPDATE_FAIL_TEAM(CONFLICT, "팀 업데이트에 실패하였습니다"),
    DUPLICATE_RESOURCE(CONFLICT, "해당 데이터가 이미 존재합니다"),
    DUPLICATE_EMAIL(CONFLICT, "해당 이메일은 이미 존재합니다."),
    LOGIN_IS_WRONG(CONFLICT, "아이디 또는 비밀번호가 잘못 입력되었습니다."),
    FILE_IS_NOT_EXIST_IN_BUCKET(CONFLICT, "해당 데이터가 존재하지 않습니다"),
    NONE_IMAGE_EXCEPTION(CONFLICT, "해당 이미지가 존재하지 않습니다"),
    REQUESTING_FILE_ALREADY_EXIST(CONFLICT, "해당 데이터가 이미 존재합니다"),

    ;


    private final HttpStatus httpStatus;
    private final String message;

}
