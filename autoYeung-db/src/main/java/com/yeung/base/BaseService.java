package com.yeung.base;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

public interface BaseService<T, PK extends Serializable>  {
    String sortColumns = "sortColumns";

    /**
     * 保存对象
     */
    void save(T o) throws DataAccessException;

    /**
     * 保存对象非空字段
     */
    void saveSelective(T o) throws DataAccessException;

    /**
     * 删除对象记录
     */
    void delete(PK id) throws DataAccessException;

    /**
     * 通过ID查询对象
     */
    T getByID(PK id) throws DataAccessException;

    /**
     * 跟新对象
     */
    int update(T model) throws DataAccessException;

    /**
     * 更新对象所有字段
     */
    void updateAll(T model) throws DataAccessException;

    /**
     * 统计数据总条数
     */
    int countAll() throws DataAccessException;

    /**
     * 获取全部对象
     */
    List<T> findAll() throws DataAccessException;

    /**
     * 查询所有的ID集合
     */
    List<PK> findAllIds() throws DataAccessException;

    /**
     * 根据此查询条件统计记录总条数
     *
     * @param parameters map 包含各种属性的查询
     */
    Long findNumberByCondition(Map<String, Object> parameters) throws DataAccessException;

    /**
     * 分页查询函数，返回对象集合
     *
     * @param parameters map 包含各种属性的查询
     *                   //     * @param rowBounds 偏移量恢复为初始值(offet:0,limit:Integer.max) 使用参考：new RowBounds(pageNum, pageSize)
     * @param offset     偏移量
     * @param pageSize   页长
     */
    List<T> findPageBreakByCondition(Map<String, Object> parameters, int offset, int pageSize) throws DataAccessException;


    /**
     * 分页查询(传pager对象,目前一级管理平台不用)
     */
    Pager<T> findList(Map<String, Object> map, Pager<T> pager) throws DataAccessException;

    /**
     * 查询符合条件的对象列表
     *
     * @param map 条件
     */
    List<T> findListByCondition(Map<String, Object> map) throws DataAccessException;

    /**
     * 通过map条件查询对象
     */
    T getByCondition(Map<String, Object> map) throws DataAccessException;
}
