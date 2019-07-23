package com.smart.common.base.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.smart.common.base.mapper.SmartBaseMapper;
import com.smart.common.base.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author LErry.li
 * Description:
 * 基于MyBatis Plus插件的IService接口的实现
 * Date: 2019/7/23 10:07
 */
public abstract class AbstractService<K extends SmartBaseMapper<T>, T> extends ServiceImpl<K, T> implements BaseService<T> {

    @Autowired
    protected K smartBaseMapper;

    /**
     * 根据条件分页查询
     *
     * @param query 分页查询条件
     * @return 分页查询结果
     */
    @Override
    public IPage<T> pageByQuery(IPage<T> query) {
        return smartBaseMapper.pageByQuery(query);
    }
}
