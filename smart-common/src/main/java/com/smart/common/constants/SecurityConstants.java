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

    public static final String SPRING_SECURITY_MOBILE_KEY = "mobile";

    public static final String SPRING_SECURITY_CODE_KEY = "code";

    /**
     * 手机验证码登录的地址
     */
    public static final String SPRING_SECURITY_MOBILE_TOKEN_URL = "/mobile/token";

    public static final String REDIS_CODE_PREFIX = "fisher_code_";

    /**
     * sys_oauth_client_details 字段
     */
    private static final String CLIENT_FIELDS = "id, client_id, client_secret, resources_ids, scope, authorized_grant_types," +
            "web_server_redirect_uri, authorities, access_token_validity," +
            "refresh_token_validity, addition_information, autoapprove";

    /**
     * jdbcClientDetailsService查询sql
     */
    private static final String BASE_FIND_STATEMENT = "select " + CLIENT_FIELDS + " from t_sys_oauth_client_details";

    /**
     * 默认查询语句
     */
    public static final String DEFAULT_FIND_STATEMENT = BASE_FIND_STATEMENT + " order by client_id";

    /**
     * 根据client_id查询
     */
    public static final String DEFAULT_FIND_STATEMENT_BY_CLIENT_ID = BASE_FIND_STATEMENT + " where client_id = ?";

}
