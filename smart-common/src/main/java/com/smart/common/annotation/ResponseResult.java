package com.smart.common.annotation;

import com.smart.common.result.PlatformResult;
import com.smart.common.result.ResponseResultInterceptor;
import com.smart.common.result.Result;

import java.lang.annotation.*;

/**
 * @author LErry.li
 * Description:
 * 接口返回结果增强  会通过拦截器拦截后放入标记，在WebResponseBodyHandler进行结果处理
 * @see ResponseResultInterceptor
 * Date: 2019/7/24 10:54
 */
@Target({ ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ResponseResult {

    Class<? extends Result>  value() default PlatformResult.class;

}