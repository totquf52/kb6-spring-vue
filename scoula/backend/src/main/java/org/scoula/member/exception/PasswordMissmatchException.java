package org.scoula.member.exception;

public class PasswordMissmatchException extends RuntimeException {

    // 생성자에서 예외 메시지를 설정함
    public PasswordMissmatchException() {
        super("비밀번호가 일치하지 않습니다.");
    }
}