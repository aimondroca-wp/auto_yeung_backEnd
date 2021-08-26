package com.yeung.base;

import org.apache.ibatis.session.RowBounds;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface BaseDao<T, PK extends Serializable> extends Serializable {

    /**
     * 保存对象
     */
    void save(T o);

    /**
     * 保存对象非空字段
     */
    void saveSelective(T o);

    /**
     * 批量添加
     */
    void batchInsert(List<T> list);

    /**
     * 删除对象记录
     */
    void delete(PK id);

    /**
     * 通过ID查询对象
     */
    T getByID(PK id);

    /**
     * 更新对象
     */
    int update(T model);

    /**
     * 更新对象所有字段
     */
    void updateAll(T model);

    /**
     * 统计数据总条数
     */
    int countAll();

    /**
     * 获取全部对象
     */
    List<T> findAll();

    /**
     * 查询所有的ID集合
     */
    List<PK> findAllIds();

    /**
     * 根据此查询条件统计记录总条数
     *
     * @param parameters map 包含各种属性的查询
     */
    Long findNumberByCondition(Map<String, Object> parameters);

    /**
     * 分页查询函数，返回对象集合
     *
     * @param parameters map 包含各种属性的查询
     * @param rowBounds  偏移量恢复为初始值(offet:0,limit:Integer.max) 使用参考：new RowBounds(pageNum, pageSize)
     */
    List<T> findPageBreakByCondition(Map<String, Object> parameters, RowBounds rowBounds);

    List<T> findPageBreakByCondition(Map<String, Object> parameters);

    /**
     * 按条件查询返回对象
     *
     * @param parameters map 包含各种属性的查询
     */
    T findByCondition(Map<String, Object> parameters);

//    /**
//     * @author Chica.Yu 20130304 add 锁表查询
//     * 分页查询函数，返回对象集合
//     * @param parameters map 包含各种属性的查询
//     * @return
//     */
    // List<T> findPageBreakByConditionForUpdate(Map<String, Object> parameters);
}
