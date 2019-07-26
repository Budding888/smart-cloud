package com.smart.auth.mobile;

import cn.hutool.core.convert.Convert;
import cn.hutool.extra.servlet.ServletUtil;
import com.smart.common.constants.SecurityConstants;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author LErry.li
 * Description:
 * 手机验证码登录filter
 * Date: 2019/7/25 20:28
 */
public class MobileAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    private String mobileParamter = SecurityConstants.SPRING_SECURITY_MOBILE_KEY;

    private String codeParamter = SecurityConstants.SPRING_SECURITY_CODE_KEY;

    private boolean postOnly = true;

    public MobileAuthenticationFilter() {
        super(SecurityConstants.SPRING_SECURITY_MOBILE_TOKEN_URL);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response){
        if (postOnly && !request.getMethod().equals(ServletUtil.METHOD_POST)) {
            throw new AuthenticationServiceException(
                    "Authentication method not supported: " + request.getMethod());
        }

        String mobile = Convert.toStr(obtainMobile(request),"").trim();

        String code = Convert.toStr(obtainCode(request),"").trim();

        MobileAuthenticationToken authRequest = new MobileAuthenticationToken(mobile, code);

        // Allow subclasses to set the "details" property
        this.setDetails(request, authRequest);

        return this.getAuthenticationManager().authenticate(authRequest);
    }

    private String obtainCode(HttpServletRequest request) {
        return request.getParameter(codeParamter);

    }

    private String obtainMobile(HttpServletRequest request) {
        return request.getParameter(mobileParamter);
    }

    private void setDetails(HttpServletRequest request,
                              MobileAuthenticationToken authRequest) {
        authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
    }


    public String getMobileParamter() {
        return mobileParamter;
    }

    public void setMobileParamter(String mobileParamter) {
        Assert.hasText(mobileParamter, "mobileParamter parameter must not be empty or null");
        this.mobileParamter = mobileParamter;
    }

    public String getCodeParamter() {
        return codeParamter;
    }

    public void setCodeParamter(String codeParamter) {
        Assert.hasText(codeParamter, "codeParamter parameter must not be empty or null");
        this.codeParamter = codeParamter;
    }

    public boolean isPostOnly() {
        return postOnly;
    }

    public void setPostOnly(boolean postOnly) {
        this.postOnly = postOnly;
    }
}
