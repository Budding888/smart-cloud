package com.smart.auth.service;

import com.smart.auth.service.impl.SysUserServiceImpl;
import com.smart.common.vo.SysUserVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author LErry.li
 * Description:
 * feign调用用户服务
 * Date: 2019/7/23 19:52
 */
@FeignClient(name = "fisher-back-service", fallback = SysUserServiceImpl.class)
public interface SysUserService {

    /**
     * 根据用户名查询用户信息
     * @param username 用户名
     * @return 用户信息
     */
    @GetMapping("/user/loadUserByUsername/{username}")
    SysUserVo loadUserByUsername(@PathVariable(value = "username") String username);
}
