package com.yeung.service;

import com.yeung.base.BaseService;
import com.yeung.base.Pager;
import com.yeung.po.YeungClientRepair;
import com.yeung.po.YeungClientRepairGroup;

import java.util.List;
import java.util.Map;

public interface YeungClientRepairService  extends BaseService<YeungClientRepair,String> {
    Pager<YeungClientRepair> findPageList(Integer pageNo, Integer pageSize, Map<String, Object> condtion);

    List<YeungClientRepairGroup> findClientRepairByGroup(Integer clientId);

    List<YeungClientRepair> findClientRepairByIds(List<Integer> ids);

    List<YeungClientRepairGroup> findClientRepairTotalPrice(Integer clientId);

    int updateByTime(Integer clientId, Long timestamp);
}
