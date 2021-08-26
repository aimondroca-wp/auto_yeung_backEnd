package com.yeung.service.impl;

import com.yeung.base.BaseDao;
import com.yeung.base.BaseServiceImpl;
import com.yeung.base.Pager;
import com.yeung.dao.YeungClientNoteDao;
import com.yeung.po.YeungClientNote;
import com.yeung.service.YeungClientNoteService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Map;

@Service
@Transactional
public class YeungClientNoteServiceImpl extends BaseServiceImpl<YeungClientNote,String> implements YeungClientNoteService {
    @Resource
    private YeungClientNoteDao yeungClientNoteDao;

    protected BaseDao<YeungClientNote, String> getDao() { return this.yeungClientNoteDao; }

    @Override
    public Pager<YeungClientNote> findPageList(Integer pageNo, Integer pageSize, Map<String, Object> condtion) {
        Pager<YeungClientNote> pager = new Pager<>();
        pager.setCurrePageNumber(pageNo + 1);
        pager.setPageSize(pageSize);

        return findList(condtion,pager);
    }

    @Override
    public int updateByTime(Integer clientId, Long timestamp) {
        return yeungClientNoteDao.updateByTime(clientId, timestamp);
    }
}
