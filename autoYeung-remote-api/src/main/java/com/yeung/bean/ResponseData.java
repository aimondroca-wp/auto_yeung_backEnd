package com.yeung.bean;

import java.io.Serializable;

public class ResponseData implements Serializable {
    public final static String SUCCESS = "SUCCESS";

    public final static String ERROR = "ERROR";

    private String code;

    private String message;

    private Object data;

    private ResponseData(){

    }

    private ResponseData(String code, String message, Object data){
        this.message = message;
        this.code = code;
        this.data = data;
    }

    private ResponseData(String code, String message){
        this.message = message;
        this.code = code;
    }

    public static ResponseData getInstance(StatusCode code){
        return new ResponseData(code.getValue(),code.getDesc());
    }

    public static ResponseData getInstance(StatusCode code, Object data){
        return new ResponseData(code.getValue(),code.getDesc(),data);
    }

    public static ResponseData getInstance(String code,String message){
        return new ResponseData(code,message);
    }

    public static ResponseData getInstance(String code,String message,Object data){
        return new ResponseData(code,message,data);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
