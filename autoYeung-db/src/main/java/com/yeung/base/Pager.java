package com.yeung.base;

import java.util.ArrayList;
import java.util.List;

public class Pager<T> {

    public static final Integer MAX_PAGE_SIZE = 100000;// 每页最大记录数限制
    public String max_count = "100000";
    private Integer currePageNumber = 1;// 当前页码
    private Integer pageSize = 15;// 每页记录数
    private Long totalCount = 0l;// 总记录数
    private Integer pageCount = 0;// 总页数
    private List<T> list;// 数据List
    private T object;
    private Integer maxShowPageCount = 4;// 默认显示 。。。多少个页数出现... 分页显示用到
    private Integer segment;// 分隔的当前页码段
    private Integer startPageNumber;// 默认显示 。。。开始页数... 分页显示用到
    private Integer endPageNumber;// 默认显示 。。。结束页数 ... 分页显示用到
    private Integer gameLimitForType = 5; // 游戏显示5条
    private String cardNumber;
    private String cardScore;
    private String cardMoney;
    private Integer firstResult;// first pagenum 显示的第一条记录是多少[开始条数]
    private List<Integer> showpagerList = new ArrayList<Integer>();// 循环显示页号码
    private List<Integer> showpagerNumList = new ArrayList<Integer>();// 循环显示页号码

    public String getGameLimitForType() {
        return String.valueOf(this.gameLimitForType);
    }

    public String getMax_count() {
        return max_count;
    }
    // 封装到LIST
    // -页面循环

    public void setMax_count(String maxCount) {
        max_count = maxCount;
    }

    public List<Integer> getShowpagerList() {
        for (int i = getStartPageNumber(); i <= getEndPageNumber(); i++) {
            showpagerList.add(i);
        }
        return showpagerList;
    }

    public List<Integer> getShowpagerNumList() {
        for (int i = 1; i <= this.getPageCount(); i++) {
            showpagerNumList.add(i);
        }
        return showpagerNumList;
    }

    public void setShowpagerNumList(List<Integer> showpagerNumList) {
        this.showpagerNumList = showpagerNumList;
    }

    public Integer getFirstResult() {
        return (currePageNumber - 1) * pageSize;
    }

    public void setFirstResult(Integer firstResult) {
        this.firstResult = firstResult;
    }

    public Integer getSegment() {
        this.segment = (currePageNumber - 1) / maxShowPageCount;
        if ((currePageNumber - 1) % maxShowPageCount >= 0) {
            segment++;
        }
        if (this.segment < 1) {
            this.segment = 1;
        }
        return segment;
    }

    public Integer getStartPageNumber() {
        this.startPageNumber = (getSegment() - 1) * maxShowPageCount + 1;
        if (this.startPageNumber < 1) {
            this.startPageNumber = 1;
        }
        return startPageNumber;
    }

    public Integer getEndPageNumber() {
        this.endPageNumber = getSegment() * maxShowPageCount;
        if (this.endPageNumber > pageCount) {
            this.endPageNumber = pageCount;
        }
        return endPageNumber;
    }

    public Integer getCurrePageNumber() {
        return currePageNumber;
    }

    public void setCurrePageNumber(Integer currePageNumber) {
        if (currePageNumber < 1) {
            currePageNumber = 1;
        }
        this.currePageNumber = currePageNumber;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        if (pageSize < 1) {
            pageSize = 1;
        } else if (pageSize > MAX_PAGE_SIZE) {
            pageSize = MAX_PAGE_SIZE;
        }
        this.pageSize = pageSize;
    }

    public Integer getPageCount() {
        pageCount = totalCount.intValue() / pageSize;
        if (totalCount % pageSize > 0) {
            pageCount++;
        }
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    public Long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getMaxShowPageCount() {
        return maxShowPageCount;
    }

    public void setMaxShowPageCount(Integer maxShowPageCount) {
        this.maxShowPageCount = maxShowPageCount;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    /**
     * 返回STRING类型在开始条数
     *
     * @return
     */
    public String getCurrePageNumberForString() {
        return String.valueOf(this.getCurrePageNumber());
    }

    public String getFirstResultForString() {
        return String.valueOf(this.getFirstResult());
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardScore() {
        return cardScore;
    }

    public void setCardScore(String cardScore) {
        this.cardScore = cardScore;
    }

    public String getCardMoney() {
        return cardMoney;
    }

    public void setCardMoney(String cardMoney) {
        this.cardMoney = cardMoney;
    }

    // 排序方式
    public enum OrderType {
        asc, desc
    }
}
