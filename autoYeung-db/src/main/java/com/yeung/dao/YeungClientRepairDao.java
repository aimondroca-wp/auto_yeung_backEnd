package com.yeung.dao;

import com.yeung.base.BaseDao;
import com.yeung.po.YeungClientRepair;
import com.yeung.po.YeungClientRepairGroup;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface YeungClientRepairDao extends BaseDao<YeungClientRepair,String> {
    List<YeungClientRepairGroup> findClientRepairByGroup(@Param("clientId") Integer clientId);
    List<YeungClientRepairGroup> findClientRepairTotalPrice(@Param("clientId") Integer clientId);
    List<YeungClientRepair> findClientRepairByIds(@Param("ids") List<Integer> ids);
    int updateByTime(@Param("clientId") Integer clientId, @Param("timestamp") Long timestamp);
}
