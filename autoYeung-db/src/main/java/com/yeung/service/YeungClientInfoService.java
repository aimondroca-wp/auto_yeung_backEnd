package com.yeung.service;

import com.yeung.base.BaseService;
import com.yeung.base.Pager;
import com.yeung.po.YeungClientInfo;

import java.util.Map;

public interface YeungClientInfoService extends BaseService<YeungClientInfo,String> {
    Pager<YeungClientInfo> findPageList(Integer pageNo, Integer pageSize, Map<String, Object> condtion);
}
