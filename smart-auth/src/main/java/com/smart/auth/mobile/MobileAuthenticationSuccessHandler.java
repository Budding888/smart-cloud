package com.smart.auth.mobile;

import cn.hutool.json.JSONUtil;
import com.smart.common.constants.CommonConstants;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.InvalidClientException;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.request.DefaultOAuth2RequestValidator;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

/**
 * @author LErry.li
 * Description:
 * 手机登陆成功处理类
 * Date: 2019/7/26 9:46
 */
@Slf4j
@Component
public class MobileAuthenticationSuccessHandler implements AuthenticationSuccessHandler{

    @Autowired
    private ClientDetailsService clientDetailsService;

    @Autowired
    private AuthorizationServerTokenServices authorizationServerTokenServices;

    /**
     * 身份验证成功时调用
     *
     * @param request        发生身份验证尝试的请求。
     * @param response       成功后的响应
     * @param authentication the <tt>Authentication</tt> object which was created during
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String clientId = request.getParameter("client_id");
        String clientSecret = request.getParameter("client_secret");


        if (clientId == null) {
            throw new BadCredentialsException("No client credentials presented");
        }

        if (clientSecret == null) {
            clientSecret = "";
        }

        clientId = clientId.trim();

        ClientDetails clientDetails = clientDetailsService.loadClientByClientId(clientId);

        if(clientDetails == null) {
            throw new InvalidClientException("Client do not exist");
        }
        if(StringUtils.isEmpty(clientDetails.getClientSecret()) || clientDetails.getClientSecret().equalsIgnoreCase(clientSecret)) {
            throw new InvalidClientException("Given client ID does not match authenticated client");
        }

        TokenRequest tokenRequest = new TokenRequest(new HashMap<>(), clientId , clientDetails.getScope(), "mobile");

        new DefaultOAuth2RequestValidator().validateScope(tokenRequest, clientDetails);

        OAuth2Request oAuth2Request = tokenRequest.createOAuth2Request(clientDetails);

        OAuth2Authentication oAuth2Authentication = new OAuth2Authentication(oAuth2Request, authentication);

        OAuth2AccessToken auth2AccessToken = authorizationServerTokenServices.createAccessToken(oAuth2Authentication);
        log.info("登录成功 token: {}", auth2AccessToken.getValue());

        response.setCharacterEncoding(CommonConstants.UTF8);
        response.setContentType(CommonConstants.CONTENT_TYPE);
        PrintWriter printWriter = response.getWriter();
        printWriter.append(JSONUtil.toJsonStr(auth2AccessToken));
    }
}
