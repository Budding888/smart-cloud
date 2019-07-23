package com.smart.common.exception;

import com.smart.common.constants.ResponseCodeEnum;
import com.smart.common.util.ConvertUtil;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.util.StringUtils;


/**
 * @author LErry.li
 * Description:
 * 业务异常类
 * Date: 2019-7-23 19:05:41
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = 2332608236621015980L;

    protected String code;

    protected String message;

    protected ResponseCodeEnum responseCodeEnum;

    protected Object data;

    public BusinessException() {

    }

    public BusinessException(String message) {
        this();
        this.message = message;
    }

    public BusinessException(String format, Object... objects) {
        this();
        format = StringUtils.replace(format, "{}", "%s");
        this.message = String.format(format, objects);
    }

    public BusinessException(ResponseCodeEnum resultCode, Object data) {
        this(resultCode);
        this.data = data;
    }

    public BusinessException(ResponseCodeEnum resultCode) {
        this.responseCodeEnum = resultCode;
        this.code = ConvertUtil.toStr(resultCode.code());
        this.message = resultCode.message();
    }
}
