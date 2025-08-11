package com.spartaschedulerdevelop.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    //인자 관련 오류
    INVALID_INPUT("VAL-001", "입력값이 유효하지 않습니다", HttpStatus.BAD_REQUEST), //ExceptionHandler 유동적으로 사용

    //유저 관련 오류
    DUPLICATE_USER("USR-001", "이미 가입된 사용자입니다", HttpStatus.CONFLICT),
    USER_NOT_FOUND("USR-003", "해당 사용자를 찾을 수 없습니다.", HttpStatus.NOT_FOUND),

    //인증 관련 오류
    INVALID_PASSWORD("AUTH-001", "비밀번호가 일치하지 않습니다", HttpStatus.BAD_REQUEST),
    UNAUTHORIZED("AUTH-002", "로그인이 필요합니다", HttpStatus.UNAUTHORIZED), //필터에서 하드코딩으로 사용
    INVALID_CREDENTIALS("AUTH-003", "아이디 또는 비밀번호가 올바르지 않습니다", HttpStatus.UNAUTHORIZED),
    FORBIDDEN_UPDATE("AUTH-004", "작성자만 수정할 수 있습니다.", HttpStatus.FORBIDDEN),
    FORBIDDEN_DELETE("AUTH-005", "작성자만 삭제할 수 있습니다.", HttpStatus.FORBIDDEN),

    //일정 관련 오류
    SCHEDULE_NOT_FOUND("SCH-001", "해당 글을 찾을 수 없습니다.", HttpStatus.NOT_FOUND),

    //코멘트 관련 오류
    COMMENT_NOT_FOUND("COMM-001", "해당 댓글을 찾을 수 없습니다.", HttpStatus.NOT_FOUND);

    private final String code;
    private final String message;
    private final HttpStatus httpStatus;
}