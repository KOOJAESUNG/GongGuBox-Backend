package com.gonggubox.exception;

import com.gonggubox.constant.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 직접 정의한 excpetion 처리 시
 */
@AllArgsConstructor
@Getter
public class AppException extends RuntimeException {
    private ErrorCode errorCode;
    private String message;
}

