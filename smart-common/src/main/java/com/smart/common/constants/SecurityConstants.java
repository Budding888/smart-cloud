package com.smart.common.constants;

/**
 * @author LErry.li
 * Description:
 * 权限相关常量
 * Date: 2019/7/23 15:46
 */
public final class SecurityConstants {

    private SecurityConstants() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * token的header key
     */
    public static final String TOKEN_HEADER = "Authorization";

    /**
     * jwt 加密key
     */
    public static final String SIGN_KEY = "FISHER";

    /**
     * jwt中 用户id的key
     */
    public static final String USER_ID = "userId";


    /**
     * jwt中 用户userName的key
     */
    public static final String USER_NAME = "userName";

    /**
     * jwt中 角色集合的key
     */
    public static final String AUTHORITIES = "authorities";

}
