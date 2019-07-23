package com.smart.common.exception;


import com.smart.common.constants.ResponseCodeEnum;

/**
 * @author LErry.li
 * Description:
 * 权限不足异常
 * Date: 2019-7-23 19:05:41
 */
public class PermissionForbiddenException extends BusinessException {
    private static final long serialVersionUID = 3721036867889297081L;

    public PermissionForbiddenException() {
        super();
    }

    public PermissionForbiddenException(Object data) {
        super.data = data;
    }

    public PermissionForbiddenException(ResponseCodeEnum resultCode) {
        super(resultCode);
    }

    public PermissionForbiddenException(ResponseCodeEnum resultCode, Object data) {
        super(resultCode, data);
    }

    public PermissionForbiddenException(String msg) {
        super(msg);
    }

    public PermissionForbiddenException(String formatMsg, Object... objects) {
        super(formatMsg, objects);
    }

}
