package com.yeung.exception;

public enum StatusCode {

    SUCCESS("0000","处理成功");

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
