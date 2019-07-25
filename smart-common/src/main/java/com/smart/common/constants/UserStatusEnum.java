package com.smart.common.constants;

/**
 * @author LErry.li
 * Description:
 * 用户状态枚举
 * Date: 2019/7/24 9:54
 */
public enum UserStatusEnum {

    /**
     * 正常
     */
    NORMAL(1, "正常"),

    /**
     * 锁定
     */
    LOCK(0,"锁定"),

    /**
     * 注销
     */
    CANCEL(-1,"锁定");


    private Integer code;

    private String message ;

    UserStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer code() {
        return code;
    }

    public String message() {
        return message;
    }

}
