package com.yeung.service.impl;

import com.yeung.base.BaseDao;
import com.yeung.base.BaseServiceImpl;
import com.yeung.base.Pager;
import com.yeung.dao.YeungClientRepairDao;
import com.yeung.po.YeungClientRepair;
import com.yeung.po.YeungClientRepairGroup;
import com.yeung.service.YeungClientRepairService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class YeungClientRepairServiceImpl extends BaseServiceImpl<YeungClientRepair,String> implements YeungClientRepairService {
    @Resource
    private YeungClientRepairDao yeungClientRepairDao;

    protected BaseDao<YeungClientRepair, String> getDao() { return this.yeungClientRepairDao; }

    @Override
    public Pager<YeungClientRepair> findPageList(Integer pageNo, Integer pageSize, Map<String, Object> condtion) {
        Pager<YeungClientRepair> pager = new Pager<>();
        pager.setCurrePageNumber(pageNo + 1);
        pager.setPageSize(pageSize);

        return findList(condtion,pager);
    }

    @Override
    public List<YeungClientRepairGroup> findClientRepairByGroup(Integer clientId) {
        return yeungClientRepairDao.findClientRepairByGroup(clientId);
    }

    @Override
    public List<YeungClientRepair> findClientRepairByIds(List<Integer> ids) {
        return yeungClientRepairDao.findClientRepairByIds(ids);
    }

    @Override
    public List<YeungClientRepairGroup> findClientRepairTotalPrice(Integer clientId) {
        return yeungClientRepairDao.findClientRepairTotalPrice(clientId);
    }

    @Override
    public int updateByTime(Integer clientId, Long timestamp) {
        return yeungClientRepairDao.updateByTime(clientId, timestamp);
    }
}
