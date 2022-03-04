package com.yeung.api;

import org.springframework.web.bind.annotation.ExceptionHandler;

public class BaseApi {
    /**
     * 统一拦截所有ajax请求的BusinessException
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(Exception.class)
    public String exceptionHandler(Exception ex) {
        int commit1;
        int commit2;
        int commit3;
        return "sorry!access error.";
    }
}
