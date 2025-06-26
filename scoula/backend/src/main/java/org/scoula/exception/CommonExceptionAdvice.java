package org.scoula.exception;

import lombok.extern.log4j.Log4j2;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
@Log4j2
@Order(1) // 예외 처리 우선순위 지정 (숫자가 작을수록 우선순위 높음)
public class CommonExceptionAdvice {

    // 404 Not Found 예외 처리
    @ExceptionHandler(NoHandlerFoundException.class)
    public String handle404(NoHandlerFoundException ex) {
        log.warn("404 예외 발생: {}", ex.getRequestURL());
        return "/resources/index.html"; // index.html로 리다이렉트
    }
}