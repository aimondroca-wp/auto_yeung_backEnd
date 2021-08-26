package com.yeung.bean;

public enum StatusCode {
    SUCCESS("0000","处理成功"),
    FAILURE("E000","处理失败"),

    ERROR("9999","未知异常"),
    SYSTEM_ERROR("S001","系统处理异常"),

    REQUEST_PARAMETER_ERROR("R001","请求参数错误"),
    REQUEST_DATA_ERROR("R002","请求数据不存在");

    private String value;

    private String desc;

    StatusCode(String value, String desc){
        this.value = value;
        this.desc = desc;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String toString(){
        return this.desc;
    }
}
