package com.smart.common.exception;


import com.smart.common.constants.ResponseCodeEnum;

/**
 * @author LErry.li
 * Description:
 * 参数无效异常
 * Date: 2019-7-23 19:05:41
 */
public class ParameterInvalidException extends BusinessException {
    private static final long serialVersionUID = 3721036867889297081L;

    public ParameterInvalidException() {
        super();
    }

    public ParameterInvalidException(Object data) {
        super();
        super.data = data;
    }

    public ParameterInvalidException(ResponseCodeEnum resultCode) {
        super(resultCode);
    }

    public ParameterInvalidException(ResponseCodeEnum resultCode, Object data) {
        super(resultCode, data);
    }

    public ParameterInvalidException(String msg) {
        super(msg);
    }

    public ParameterInvalidException(String formatMsg, Object... objects) {
        super(formatMsg, objects);
    }

}
