package com.study.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

/*
 * @RestControllerAdvice : 이 어노테이션은 @ControllerAdvice에 @ResponseBody가 적용된 형태
 * 						@ControllerAdvice는 컨트롤러 전역에서 발생할 수 있는 예외를 잡아 throw 해주고, @ExceptionHandler는 특정 클래스에서 발생할 수 있는 예외를 잡아 throw 해준다.
 * 						일반적으로 @ExceptionHandler는 @ControllerAdvice가 선언된 클래스에 포함된 메서드에 선언한다. 
 * 
 * @Slf4j : 롬복에서 제공해주는 기능으로, 해당 어노테이션이 선언된 클래스에 자동으로 로그 객체를 생성한다. 
 * 
 * @ExceptionHandler : 이 어노테이션에 지정된 예외와 동일한 예외, 즉 RuntimeException이 발생하면 GlobalExceptionHandler는 handleRuntimeException()메서드를 실행한다. 
 * 
 * ResponseEntity<ErrorResponse> : Http Request에 대한 응답 데이터를 포함하는 클래스로, <Type>에 해당하는 데이터와 HTTP 상태 코드를 함께 리턴할 수 있다. 
*/

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

	/*
     * Developer Custom Exception
     */
    @ExceptionHandler(CustomException.class)
    protected ResponseEntity<ErrorResponse> handleCustomException(final CustomException e) {
        log.error("handleCustomException: {}", e.getErrorCode());
        return ResponseEntity
                .status(e.getErrorCode().getStatus().value())
                .body(new ErrorResponse(e.getErrorCode()));
    }

    /*
     * HTTP 405 Exception
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    protected ResponseEntity<ErrorResponse> handleHttpRequestMethodNotSupportedException(final HttpRequestMethodNotSupportedException e) {
        log.error("handleHttpRequestMethodNotSupportedException: {}", e.getMessage());
        return ResponseEntity
                .status(ErrorCode.METHOD_NOT_ALLOWED.getStatus().value())
                .body(new ErrorResponse(ErrorCode.METHOD_NOT_ALLOWED));
    }

    /*
     * HTTP 500 Exception
     */
    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ErrorResponse> handleException(final Exception e) {
        log.error("handleException: {}", e.getMessage());
        return ResponseEntity
                .status(ErrorCode.INTERNAL_SERVER_ERROR.getStatus().value())
                .body(new ErrorResponse(ErrorCode.INTERNAL_SERVER_ERROR));
    }

}