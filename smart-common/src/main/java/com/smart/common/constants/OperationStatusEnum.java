package com.smart.common.constants;

/**
 * @author LErry.li
 * Description:
 * 操作状态枚举
 * Date: 2019/7/22 20:21
 */
public enum OperationStatusEnum {

    /**
     * 成功
     */
    SUCCESS(0, "成功"),
    /**
     * 失败
     */
    FAIL(-1, "失败");


    private Integer code;

    private String message;

    OperationStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer code() {
        return this.code;
    }

    public String message() {
        return this.message;
    }
}
