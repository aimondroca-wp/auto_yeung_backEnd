package com.yeung.base;

import com.yeung.exception.BusinessException;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.dao.DataAccessException;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public abstract class BaseServiceImpl<T, PK extends Serializable> implements BaseService<T, PK> {

    protected abstract BaseDao<T, PK> getDao();

    @Override
    public void save(T o) throws DataAccessException {
        //判断对象是否为空
        if (null == o) {
            throw new BusinessException("操作对象不能为空");
        }
        this.getDao().save(o);
    }

    @Override
    public void saveSelective(T o) throws DataAccessException {
        //判断对象是否为空
        if (null == o) {
            throw new BusinessException("操作对象不能为空");
        }
        this.getDao().saveSelective(o);
    }

    @Override
    public void delete(PK id) throws DataAccessException {
        //判断编号是否为空
        if (id == null || StringUtils.isBlank(id.toString())) {
            throw new BusinessException("操作编号不能为空");
        }
        this.getDao().delete(id);
    }

    @Override
    public T getByID(PK id) throws DataAccessException {
        //判断编号是否为空
        if (id == null || StringUtils.isBlank(id.toString())) {
            throw new BusinessException("操作编号不能为空");
        }

        return this.getDao().getByID(id);
    }

    @Override
    public int update(T model) throws DataAccessException {
        //判断对象是否为空
        if (null == model) {
            throw new BusinessException("操作对象不能为空");
        }

        return this.getDao().update(model);
    }

    @Override
    public void updateAll(T model) throws DataAccessException {
        //判断对象是否为空
        if (null == model) {
            throw new BusinessException("操作对象不能为空");
        }

        this.getDao().updateAll(model);
    }

    @Override
    public int countAll() throws DataAccessException {
        return this.getDao().countAll();
    }

    @Override
    public List<T> findAll() throws DataAccessException {
        return this.getDao().findAll();
    }

    @Override
    public List<PK> findAllIds() throws DataAccessException {
        return this.getDao().findAllIds();
    }

    @Override
    public Long findNumberByCondition(Map<String, Object> parameters) throws DataAccessException {
        //判断对象是否为空
        if (null == parameters) {
            throw new BusinessException("参数不能为空");
        }
        return this.getDao().findNumberByCondition(parameters);
    }

    @Override
    public List<T> findPageBreakByCondition(Map<String, Object> parameters, int offset, int pageSize) throws DataAccessException {
        //判断对象是否为空
        if (null == parameters) {
            throw new BusinessException("参数不能为空");
        }
        return this.getDao().findPageBreakByCondition(parameters, new RowBounds(offset, pageSize));
    }


    @Override
    public Pager<T> findList(Map<String, Object> map, Pager<T> pager) throws DataAccessException {

        //判断对象是否为空
        if (null == map) {
            throw new BusinessException("参数不能为空");
        }
        //rowBounds 偏移量恢复为初始值(offet:0,limit:Integer.max) 使用参考：new RowBounds(pageNum, pageSize)
        pager.setTotalCount(this.getDao().findNumberByCondition(map));
        List<T> result = this.getDao().findPageBreakByCondition(map, new RowBounds(pager.getFirstResult(), pager.getPageSize()));
        pager.setList(result);
        return pager;
    }

    @Override
    public List<T> findListByCondition(Map<String, Object> map) throws DataAccessException {
        return this.getDao().findPageBreakByCondition(map);
    }

    /**
     * 通过map条件查询对象
     */
    public T getByCondition(Map<String, Object> map) throws DataAccessException {
        return this.getDao().findByCondition(map);
    }
}
