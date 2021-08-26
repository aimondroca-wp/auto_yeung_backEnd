package com.yeung.service;

import com.yeung.base.BaseService;
import com.yeung.base.Pager;
import com.yeung.po.YeungClientNote;

import java.util.Map;

public interface YeungClientNoteService extends BaseService<YeungClientNote,String> {
    Pager<YeungClientNote> findPageList(Integer pageNo, Integer pageSize, Map<String, Object> condtion);

    int updateByTime(Integer clientId, Long timestamp);
}
