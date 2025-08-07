package com.spartaschedulerdevelop.common.exception.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    //인자 관련 오류
    INVALID_INPUT("VAL-001", "입력값이 유효하지 않습니다"),

    //유저 관련 오류
    DUPLICATE_USER("USR-001", "이미 가입된 사용자입니다"),
    USER_LIMIT_EXCEEDED("USR-002", "가입 가능한 사용자 수를 초과했습니다"),

    //일정 관련 오류
    SCHEDULE_NOT_FOUND("SCH-001", "해당 글을 찾을 수 없습니다.");

    private final String code;
    private final String message;
}

