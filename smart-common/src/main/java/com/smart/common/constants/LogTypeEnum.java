package com.smart.common.constants;

/**
 * @author LErry.li
 * Description:
 * <p>
 * Date: 2019/7/26 11:09
 */
public enum LogTypeEnum {

    LOGIN("0", "登录日志"),
    OPERATOR("1","操作日志");


    private String code;

    private String message;

    LogTypeEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String code() {
        return code;
    }

    public String message() {
        return message;
    }

}
