package com.smart.auth.mobile;


import com.smart.auth.security.UserDetailsImpl;
import com.smart.auth.service.SysUserService;
import com.smart.common.constants.SecurityConstants;
import com.smart.common.vo.SysUserVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * @author LErry.li
 * Description:
 * 手机验证码登录逻辑实现
 * Date: 2019/7/25 20:46
 */
@Slf4j
public class MobileAuthenticationProvider implements AuthenticationProvider {

    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private SysUserService sysUserService;

    /**
     * Performs authentication with the same contract as
     * {@link AuthenticationManager#authenticate(Authentication)}
     * .
     *
     * @param authentication the authentication request object.
     * @return a fully authenticated object including credentials. May return
     * <code>null</code> if the <code>AuthenticationProvider</code> is unable to support
     * authentication of the passed <code>Authentication</code> object. In such a case,
     * the next <code>AuthenticationProvider</code> that supports the presented
     * <code>Authentication</code> class will be tried.
     * @throws AuthenticationException if authentication fails.
     */
    @Override
    public Authentication authenticate(Authentication authentication){
        MobileAuthenticationToken mobileAuthenticationToken = (MobileAuthenticationToken) authentication;
        String mobile = mobileAuthenticationToken.getPrincipal().toString();
        String realCode = redisTemplate.opsForValue().get(SecurityConstants.REDIS_CODE_PREFIX + mobile);
        String inputCode = authentication.getCredentials().toString();
        // 判断手机的验证码是否存在
        if (realCode == null) {
            log.debug("登录失败，当前手机号验证码不存在或者已经过期");
            throw new BadCredentialsException("登录失败，验证码不存在");
        }
        // 判断是否验证码跟redis中存的验证码是否正确
        if(!inputCode.equalsIgnoreCase(realCode)) {
            log.debug("登录失败，您输入的验证码不正确");

            throw new BadCredentialsException("登录失败，验证码不正确");
        }
        SysUserVo sysUserVo = sysUserService.loadUserByMobile(mobile);
        if(sysUserVo == null) {
            log.debug("登录失败，用户不存在");
            throw new UsernameNotFoundException("登录失败, 手机号码不存在");
        }

        UserDetailsImpl userDetails = new UserDetailsImpl(sysUserVo);
        // 重新构造token  登录成功
        MobileAuthenticationToken authenticationToken = new MobileAuthenticationToken(userDetails, inputCode, userDetails.getAuthorities());
        authenticationToken.setDetails(mobileAuthenticationToken.getDetails());
        return authenticationToken;
    }

    /**
     * Returns <code>true</code> if this <Code>AuthenticationProvider</code> supports the
     * indicated <Code>Authentication</code> object.
     * <p>
     * Returning <code>true</code> does not guarantee an
     * <code>AuthenticationProvider</code> will be able to authenticate the presented
     * instance of the <code>Authentication</code> class. It simply indicates it can
     * support closer evaluation of it. An <code>AuthenticationProvider</code> can still
     * return <code>null</code> from the {@link #authenticate(Authentication)} method to
     * indicate another <code>AuthenticationProvider</code> should be tried.
     * </p>
     * <p>
     * Selection of an <code>AuthenticationProvider</code> capable of performing
     * authentication is conducted at runtime the <code>ProviderManager</code>.
     * </p>
     *
     * @param authentication
     * @return <code>true</code> if the implementation can more closely evaluate the
     * <code>Authentication</code> class presented
     */
    @Override
    public boolean supports(Class<?> authentication) {
        return MobileAuthenticationToken.class.isAssignableFrom(authentication);
    }

    public RedisTemplate<String, String> getRedisTemplate() {
        return redisTemplate;
    }

    public void setRedisTemplate(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public SysUserService getSysUserService() {
        return sysUserService;
    }

    public void setSysUserService(SysUserService sysUserService) {
        this.sysUserService = sysUserService;
    }
}
