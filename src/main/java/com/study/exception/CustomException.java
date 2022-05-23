package com.study.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

/*Custom 예외 처리용 클래스
* ErrorResponse와 마찬가지로 ErrorCode를 통한 객체 생성만을 허용한다.
* Unchecked Exception인 RuntimeException을 상속받는 것을 꼭 기억하자.
*/
@Getter
@AllArgsConstructor
public class CustomException extends RuntimeException {

    private final ErrorCode errorCode;

}
