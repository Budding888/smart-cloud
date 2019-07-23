package com.smart.common.exception;

import com.smart.common.constants.ResponseCodeEnum;

/**
 * @author LErry.li
 * Description:
 * 远程访问时错误
 * Date: 2019-7-23 19:05:41
 */
public class RemoteAccessException extends BusinessException {
    private static final long serialVersionUID = -832464574076215195L;

    public RemoteAccessException() {
        super();
    }

    public RemoteAccessException(Object data) {
        super.data = data;
    }

    public RemoteAccessException(ResponseCodeEnum resultCode) {
        super(resultCode);
    }

    public RemoteAccessException(ResponseCodeEnum resultCode, Object data) {
        super(resultCode, data);
    }

    public RemoteAccessException(String msg) {
        super(msg);
    }

    public RemoteAccessException(String formatMsg, Object... objects) {
        super(formatMsg, objects);
    }

}
