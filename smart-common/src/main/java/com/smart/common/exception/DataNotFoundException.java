package com.smart.common.exception;


import com.smart.common.constants.ResponseCodeEnum;

/**
 * @author LErry.li
 * Description:
 * 数据未找到
 * Date: 2019-7-23 19:05:41
 */
public class DataNotFoundException extends BusinessException {
    private static final long serialVersionUID = 3721036867889297081L;

    public DataNotFoundException() {
        super();
    }

    public DataNotFoundException(Object data) {
        super();
        super.data = data;
    }

    public DataNotFoundException(ResponseCodeEnum resultCode) {
        super(resultCode);
    }

    public DataNotFoundException(ResponseCodeEnum resultCode, Object data) {
        super(resultCode, data);
    }

    public DataNotFoundException(String msg) {
        super(msg);
    }

    public DataNotFoundException(String formatMsg, Object... objects) {
        super(formatMsg, objects);
    }

}
