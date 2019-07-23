package com.smart.common.annotation;

import com.smart.common.aspect.ServiceLogAspect;

import java.lang.annotation.*;

/**
 * @author LErry.li
 * Description:
 * AOP日志记录，自定义注解
 * @see ServiceLogAspect
 * Date: 2019-7-22 19:04:35
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ServiceLog {

    /**
     * 服务Key
     */
    String serviceKey();

    /**
     * 模块名称
     */
    String moduleName();

    /**
     * 操作名称
     */
    String actionName();
}
