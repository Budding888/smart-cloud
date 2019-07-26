package com.smart.common.result;

import com.smart.common.constants.ResponseCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * @author LErry.li
 * Description:
 * 通用统一返回结果
 * Date: 2019/7/22 20:11
 */
@Builder
@AllArgsConstructor
@Data
public class PlatformResult<T> implements Result {

    private transient T data;

    private Integer code = ResponseCodeEnum.SUCCESS.code();

    private String message = ResponseCodeEnum.SUCCESS.message();

    public PlatformResult() {
    }

    public PlatformResult(T data) {
        this.data = data;
    }

    public PlatformResult(T data, String message) {
        this.data = data;
        this.message = message;
    }

    public PlatformResult(T data, ResponseCodeEnum responseCode) {
        this.data = data;
        this.code = responseCode.code();
        this.message = responseCode.message();
    }

    public PlatformResult(Throwable throwable) {
        this.message = throwable.getMessage();
        this.code = ResponseCodeEnum.FAIL.code();
    }

    public PlatformResult(Throwable throwable, ResponseCodeEnum code) {
        this.message = throwable.getMessage();
        this.code = code.code();
    }
}
