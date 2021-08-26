package com.yeung.service.impl;

import com.yeung.base.BaseDao;
import com.yeung.base.BaseServiceImpl;
import com.yeung.base.Pager;
import com.yeung.dao.YeungClientInfoDao;
import com.yeung.po.YeungClientInfo;
import com.yeung.service.YeungClientInfoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Map;

@Service
@Transactional
public class YeungClientInfoServiceImpl extends BaseServiceImpl<YeungClientInfo,String> implements YeungClientInfoService {
    @Resource
    private YeungClientInfoDao yeungClientInfoDao;

    protected BaseDao<YeungClientInfo, String> getDao() { return this.yeungClientInfoDao; }

    @Override
    public Pager<YeungClientInfo> findPageList(Integer pageNo, Integer pageSize, Map<String, Object> condtion) {
        Pager<YeungClientInfo> pager = new Pager<>();
        pager.setCurrePageNumber(pageNo + 1);
        pager.setPageSize(pageSize);

        return findList(condtion,pager);
    }
}
