package com.smart.common.base.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author LErry.li
 * Description:
 * 通用Service
 * Date: 2019/7/23 10:06
 */
public interface BaseService<T> extends IService<T> {

    /**
     * 根据条件分页查询
     *
     * @param query 分页查询条件
     * @return 分页查询结果
     */
    IPage<T> pageByQuery(IPage<T> query);
}
