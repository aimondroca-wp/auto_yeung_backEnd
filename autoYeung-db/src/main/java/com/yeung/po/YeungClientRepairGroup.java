package com.yeung.po;

import com.yeung.base.BaseModel;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;

public class YeungClientRepairGroup extends BaseModel {
    private static final long serialVersionUID = 5454155825314635342L;

    private String ids;
    private int clientId;
    private BigDecimal totalPrice;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private java.util.Date GroupDate;
    private long timestamp;
    private int is_enable;

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Date getGroupDate() {
        return GroupDate;
    }

    public void setGroupDate(Date groupDate) {
        GroupDate = groupDate;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
