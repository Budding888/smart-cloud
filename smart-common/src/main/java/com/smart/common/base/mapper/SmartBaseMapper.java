package com.smart.common.base.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 * @author LErry.li
 * Description:
 * 通用Mapper
 * Date: 2019/7/23 10:08
 */
public interface SmartBaseMapper<T> extends BaseMapper<T> {

    /**
     * 根据条件分页查询
     *
     * @param query 分页查询条件
     * @return 分页查询结果
     */
    IPage<T> pageByQuery(IPage<T> query);

}
