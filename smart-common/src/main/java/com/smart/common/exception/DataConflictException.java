package com.smart.common.exception;


import com.smart.common.constants.ResponseCodeEnum;

/**
 * @author LErry.li
 * Description:
 * 数据已存在
 * Date: 2019-7-23 19:05:41
 */
public class DataConflictException extends BusinessException {
    private static final long serialVersionUID = 3721036867889297081L;

    public DataConflictException() {
        super();
    }

    public DataConflictException(Object data) {
        super();
        super.data = data;
    }

    public DataConflictException(ResponseCodeEnum resultCode) {
        super(resultCode);
    }

    public DataConflictException(ResponseCodeEnum resultCode, Object data) {
        super(resultCode, data);
    }

    public DataConflictException(String msg) {
        super(msg);
    }

    public DataConflictException(String formatMsg, Object... objects) {
        super(formatMsg, objects);
    }

}
