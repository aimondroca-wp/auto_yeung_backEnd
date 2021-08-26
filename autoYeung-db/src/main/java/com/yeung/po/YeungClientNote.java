package com.yeung.po;

import com.yeung.base.BaseModel;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

public class YeungClientNote extends BaseModel {
    private static final long serialVersionUID = 5454155825314635342L;

    private int id;
    private int clientId;
    private BigDecimal deposit;
    private String note;
    private long timestamp;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private java.util.Date createTime;
    private String createDate;
    private BigDecimal totalPriceIncludeTax;
    private BigDecimal totalPrice;
    private int isEnable;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public BigDecimal getDeposit() {
        return deposit;
    }

    public void setDeposit(BigDecimal deposit) {
        this.deposit = deposit;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public BigDecimal getTotalPriceIncludeTax() {
        return totalPriceIncludeTax;
    }

    public void setTotalPriceIncludeTax(BigDecimal totalPriceIncludeTax) {
        this.totalPriceIncludeTax = totalPriceIncludeTax;
    }

    public int getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(int isEnable) {
        this.isEnable = isEnable;
    }
}
