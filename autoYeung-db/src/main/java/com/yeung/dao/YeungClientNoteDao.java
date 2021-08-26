package com.yeung.dao;

import com.yeung.base.BaseDao;
import com.yeung.po.YeungClientNote;
import org.apache.ibatis.annotations.Param;

public interface YeungClientNoteDao extends BaseDao<YeungClientNote,String> {
    int updateByTime(@Param("clientId") Integer clientId, @Param("timestamp") Long timestamp);
}
