package com.smart.common.base.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.smart.common.base.service.BaseService;
import com.smart.common.result.PlatformResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;

/**
 * @author LErry.li
 * Description:
 * 通用控制器
 * Date: 2019/7/23 10:06
 */
public class BaseController<K extends BaseService<T>, T, P extends Serializable> {

    @Autowired
    private K baseService;

    @ApiOperation(value = "添加", httpMethod = "POST")
    @PostMapping
    public PlatformResult<Boolean> save(@RequestBody T t) {
        return new PlatformResult<>(baseService.save(t));
    }

    @ApiOperation(value = "修改", httpMethod = "PUT")
    @PutMapping
    public PlatformResult<Boolean> update(@RequestBody T t) {
        return new PlatformResult<>(baseService.updateById(t));
    }

    @ApiOperation(value = "删除", httpMethod = "DELETE")
    @DeleteMapping("/{id}")
    public PlatformResult<Boolean> delete(@PathVariable("id") P id) {
        return new PlatformResult<>(baseService.removeById(id));
    }

    @ApiOperation(value = "根据主键查询", notes = "根据主键查询", httpMethod = "GET")
    @GetMapping("/{id}")
    public PlatformResult<T> getSysRoleInfo(@PathVariable("id") Integer id) {
        return new PlatformResult<>(baseService.getById(id));
    }

    @ApiOperation(value = "分页查询", notes = "分页查询", httpMethod = "GET")
    @GetMapping("/page")
    public PlatformResult<IPage<T>> pageByQuery(IPage<T> sysRoleQuery) {
        return new PlatformResult<>(baseService.pageByQuery(sysRoleQuery));
    }

    @ApiOperation(value = "查询所有信息", notes = "查询所有信息", httpMethod = "GET")
    @GetMapping
    public PlatformResult<Collection<T>> selectAll() {
        return new PlatformResult<>(baseService.listByMap(new HashMap<>()));
    }
}
