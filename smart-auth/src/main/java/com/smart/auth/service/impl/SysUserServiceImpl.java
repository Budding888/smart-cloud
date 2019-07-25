package com.smart.auth.service.impl;

import com.smart.auth.service.SysUserService;
import com.smart.common.vo.SysUserVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author LErry.li
 * Description:
 * 用户服务实现类
 * Date: 2019/7/23 19:53
 */
@Slf4j
@Service
public class SysUserServiceImpl implements SysUserService {

    /**
     * 根据用户名查询用户信息
     *
     * @param username 用户名
     * @return 用户信息
     */
    @Override
    public SysUserVo loadUserByUsername(String username) {
        log.info("根据用户名查询用户信息入参:{}", username);
        return null;
    }
}
