package com.yeung.exception;

public class BusinessException extends RuntimeException {

    private String message;

    private String code;

    public BusinessException(String message) {
        super(message);
        this.message = message;
    }

    public BusinessException(StatusCode code){
        super(code.toString());
        this.code = code.getValue();
        this.message = code.toString();
    }


    public  static BusinessException instance(StatusCode code){
        return new BusinessException(code);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
