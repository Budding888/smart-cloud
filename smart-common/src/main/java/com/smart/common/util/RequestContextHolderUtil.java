package com.smart.common.util;

import com.smart.common.constants.SecurityConstants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Base64;

/**
 * @author LErry.li
 * Description:
 * 应用级对象获取工具类
 * Date: 2019/7/23 15:44
 */
@Slf4j
public class RequestContextHolderUtil {

    private RequestContextHolderUtil() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * 获取HttpServletRequest
     *
     * @return HttpServletRequest
     */
    public static HttpServletRequest getRequest() {
        return getRequestAttributes().getRequest();
    }

    public static HttpServletResponse getResponse() {
        return getRequestAttributes().getResponse();
    }

    public static HttpSession getSession() {
        return getRequest().getSession();
    }

    /**
     * 获取请求中的token
     *
     * @param request
     * @return token
     */
    public static String getToken(HttpServletRequest request) {
        String token = "";
        String authorization = request.getHeader(SecurityConstants.TOKEN_HEADER);
        if (authorization != null) {
            token = authorization.split(" ")[1];
        }
        if (request.getParameter("access_token") != null) {
            token = request.getParameter("access_token");
        }
        log.info("获取token成功，值为{}", token);
        return token;
    }

    /**
     * 获取jwt中的claims信息
     *
     * @param token
     * @return claim
     */
    public static Claims getClaims(String token) {
        String key = Base64.getEncoder().encodeToString(SecurityConstants.SIGN_KEY.getBytes());
        return Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
    }

    /**
     * 获取请求中的userId
     * @param request 请求
     * @return userId
     */
    public static String getUserName(HttpServletRequest request){
        String token = getToken(request);
        if(token == null){
            return null;
        }
        Claims claims = getClaims(token);
        String username = (String) claims.get(SecurityConstants.USER_NAME);
        log.info("获取username成功，值为:{}", username);
        return username;
    }

    private static ServletRequestAttributes getRequestAttributes() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes());
    }

}
