package com.koreatech.byeongcheonairlineapi.exception;

public class UnAuthorizeException extends RuntimeException{
    private static final long serialVersionUID = -2238030302650813813L;
    public UnAuthorizeException() {
        super("access-token 만료!, access token을 재발급 받으세요");
    }
}
