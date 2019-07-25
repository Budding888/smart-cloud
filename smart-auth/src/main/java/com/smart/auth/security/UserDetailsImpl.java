package com.smart.auth.security;

import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import com.smart.common.constants.UserStatusEnum;
import com.smart.common.vo.SysRoleVo;
import com.smart.common.vo.SysUserVo;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author LErry.li
 * Description:
 * <p>
 * Date: 2019/7/23 20:14
 */
@Data
public class UserDetailsImpl implements UserDetails {

    private Integer userId;
    private String username;
    private String password;
    private Integer status;
    private List<SysRoleVo> roleVos;

    public UserDetailsImpl(SysUserVo userVo) {
        this.userId = userVo.getUserId();
        this.username = userVo.getUsername();
        this.password = userVo.getPassword();
        this.status = userVo.getStatus();
        this.roleVos = userVo.getSysRoleVoList();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorityList = new ArrayList<>();
        roleVos.forEach(role ->
            authorityList.add(new SimpleGrantedAuthority(role.getRoleCode()))
        );
        return authorityList;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !UserStatusEnum.LOCK.code().equals(this.status);
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
