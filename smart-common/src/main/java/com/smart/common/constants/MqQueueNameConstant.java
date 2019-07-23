package com.smart.common.constants;

/**
 * @author LErry.li
 * Description:
 * MQ队列定义
 * Date: 2019/7/22 20:01
 */
public final class MqQueueNameConstant {

    private MqQueueNameConstant() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * 系统日志队列
     */
    public static final String SYS_LOG_QUEUE = "sys_log_queue";

    /**
     * 短信验证码队列
     */
    public static final String MOBILE_CODE_QUEUE = "mobile_code_queue";

    /**
     * 邮件队列
     */
    public static final String MAIL_CODE_QUEUE = "mail_code_queue";
}
