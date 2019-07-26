package com.smart.auth.mobile;

import cn.hutool.json.JSONUtil;
import com.smart.common.constants.CommonConstants;
import com.smart.common.constants.ResponseCodeEnum;
import com.smart.common.result.PlatformResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author LErry.li
 * Description:
 * 手机登陆异常处理类
 * Date: 2019/7/25 20:03
 */
@Slf4j
@Component
public class MobileAuthenticationFailHandler implements AuthenticationFailureHandler {

    /**
     * 身份验证失败时调用
     *
     * @param request   发生身份验证尝试的请求。
     * @param response  失败后的响应
     * @param exception 为拒绝身份验证而引发的异常
     */
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        log.info("登录失败 原因: {}", exception.getMessage());
        response.setCharacterEncoding(CommonConstants.UTF8);
        response.setContentType(CommonConstants.CONTENT_TYPE);
        PrintWriter printWriter = response.getWriter();
        printWriter.append(JSONUtil.toJsonStr(new PlatformResult<>(exception.getMessage(), ResponseCodeEnum.FAIL)));
    }
}
