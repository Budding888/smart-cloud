package com.smart.common.result;

import cn.hutool.core.util.StrUtil;
import com.smart.common.constants.ResponseCodeEnum;
import com.smart.common.exception.BusinessException;
import com.smart.common.util.RequestContextHolderUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.Date;

/**
 * @author LErry.li
 * Description:
 * 默认全局错误返回结果
 * Date: 2019-7-23 19:05:41
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class DefaultErrorResult implements Result {
    private static final long serialVersionUID = 1899083570489722793L;

    /**
     * HTTP响应状态码 {@link org.springframework.http.HttpStatus}
     */
    private Integer status;

    /**
     * HTTP响应状态码的英文提示
     */
    private String error;

    /**
     * 异常堆栈的精简信息
     */
    private String message;

    /**
     * 我们系统内部自定义的返回值编码，{@link ResponseCodeEnum} 它是对错误更加详细的编码
     * <p>
     * 备注：spring boot默认返回异常时，该字段为null
     */
    private Integer code;

    /**
     * 调用接口路径
     */
    private String path;

    /**
     * 异常的名字
     */
    private String exception;

    /**
     * 异常的错误传递的数据
     */
    private Object errors;

    /**
     * 时间戳
     */
    private Date timestamp;

    public static DefaultErrorResult failure(ResponseCodeEnum resultCode, Throwable e, HttpStatus httpStatus, Object errors) {
        DefaultErrorResult result = DefaultErrorResult.failure(resultCode, e, httpStatus);
        result.setErrors(errors);
        return result;
    }

    public static DefaultErrorResult failure(ResponseCodeEnum resultCode, Throwable e, HttpStatus httpStatus) {
        DefaultErrorResult result = new DefaultErrorResult();
        result.setCode(resultCode.code());
        result.setMessage(resultCode.message());
        result.setStatus(httpStatus.value());
        result.setError(httpStatus.getReasonPhrase());
        result.setException(e.getClass().getName());
        result.setPath(RequestContextHolderUtil.getRequest().getRequestURI());
        result.setTimestamp(new Date());
        return result;
    }

    public static DefaultErrorResult failure(BusinessException e) {
        DefaultErrorResult defaultErrorResult = DefaultErrorResult.failure(e.getResponseCodeEnum() == null ? ResponseCodeEnum.SUCCESS : e.getResponseCodeEnum(), e, HttpStatus.OK, e.getData());
        if (StrUtil.isNotEmpty(e.getMessage())) {
            defaultErrorResult.setMessage(e.getMessage());
        }
        return defaultErrorResult;
    }
}
