package com.smart.auth.config;

import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;
import com.smart.auth.mobile.MobileAuthenticationFilter;
import com.smart.auth.mobile.MobileAuthenticationProvider;
import com.smart.auth.security.UserDetailsServiceImpl;
import com.smart.auth.service.SysUserService;
import com.smart.common.config.IgnoreUrlConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 * @author LErry.li
 * Description:
 * webSecurity配置
 * Date: 2019/7/25 18:22
 */
@EnableWebSecurity
@Slf4j
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private IgnoreUrlConfig ignoreUrlConfig;

    @Autowired
    private AuthenticationSuccessHandler authenticationSuccessHandler;

    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Bean
    public AuthenticationProvider mobileAuthenticationProvider() {
        MobileAuthenticationProvider mobileAuthenticationProvider = new MobileAuthenticationProvider();
        mobileAuthenticationProvider.setSysUserService(sysUserService);
        mobileAuthenticationProvider.setRedisTemplate(redisTemplate);
        return mobileAuthenticationProvider;
    }


    @Override
    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .authenticationProvider(mobileAuthenticationProvider())
                .userDetailsService(userDetailsService())
                .passwordEncoder(passwordEncoder());
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config
                = http.requestMatchers().anyRequest()
                .and()
                .authorizeRequests();

        ignoreUrlConfig.getUrls().forEach(e ->
            config.antMatchers(e).permitAll()
        );
        config
                .antMatchers("/auth/**").permitAll()
                .antMatchers("/actuator/**").permitAll()
                .anyRequest().authenticated()
//                .and()
//               .headers().frameOptions().disable()
                .and().csrf().disable();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


    /**
     * 自定义登陆过滤器
     * @return
     */
    @Bean
    public MobileAuthenticationFilter mobileAuthenticationFilter() {
        MobileAuthenticationFilter filter = new MobileAuthenticationFilter();
        try {
            filter.setAuthenticationManager(this.authenticationManagerBean());
        } catch (Exception e) {
            log.error("自定义登陆处理失败", e);
        }
        filter.setAuthenticationSuccessHandler(authenticationSuccessHandler);
        filter.setAuthenticationFailureHandler(authenticationFailureHandler);
        return filter;
    }
}
