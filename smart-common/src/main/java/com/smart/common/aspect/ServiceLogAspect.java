package com.smart.common.aspect;

import cn.hutool.extra.servlet.ServletUtil;
import cn.hutool.json.JSONUtil;
import com.smart.common.annotation.ServiceLog;
import com.smart.common.constants.MqQueueNameConstant;
import com.smart.common.constants.OperationStatusEnum;
import com.smart.common.dto.SysLogDTO;
import com.smart.common.util.ExceptionUtil;
import com.smart.common.util.IpUtil;
import com.smart.common.util.RequestContextHolderUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Objects;

/**
 * @author LErry.li
 * Description:
 * 日志处理切面
 * Date: 2019/7/22 20:01
 */
@Aspect
@Component
@Slf4j
public class ServiceLogAspect {

    private final RabbitTemplate rabbitTemplate;

    public ServiceLogAspect(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Around("execution(public com.smart.common.result.PlatformResult *(..))")
    public Object handlerControllerMethod(ProceedingJoinPoint pjp) {
        Object result = null;
        Signature signature = pjp.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method targetMethod = methodSignature.getMethod();

        long startTime = System.currentTimeMillis();
        SysLogDTO sysLogDTO = new SysLogDTO();
        // 需要记录日志存库
        boolean isAnnotationPresent = targetMethod.isAnnotationPresent(ServiceLog.class);
        if (isAnnotationPresent) {
            HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();

            // 获取注解
            ServiceLog sysLog = targetMethod.getAnnotation(ServiceLog.class);
            sysLogDTO
                    .setServiceKey(sysLog.serviceKey())
                    .setModuleName(sysLog.moduleName())
                    .setActionName(sysLog.actionName())
                    .setParams(JSONUtil.toJsonStr(request.getParameterMap()))
                    .setRemoteAddr(IpUtil.getRealIp(request))
                    .setMethod(request.getMethod())
                    .setRequestUri(request.getRequestURI())
                    .setUserAgent(request.getHeader("user-agent"))
                    .setCreateBy(RequestContextHolderUtil.getUserName(request));
        }
        try {
            result = pjp.proceed();
            sysLogDTO.setStatus(OperationStatusEnum.SUCCESS.code());
        } catch (Throwable e) {
            sysLogDTO.setException(ExceptionUtil.getTrace(e));
            sysLogDTO.setStatus(OperationStatusEnum.FAIL.code());
        }
        // 本次操作用时（毫秒）
        long elapsedTime = System.currentTimeMillis() - startTime;
        log.info("[{}]use time: {}", pjp.getSignature(), elapsedTime);
        sysLogDTO.setTime(String.valueOf(elapsedTime));

        // 发送消息到 系统日志队列
        if (isAnnotationPresent) {
            rabbitTemplate.convertAndSend(MqQueueNameConstant.SYS_LOG_QUEUE, sysLogDTO);
        }
        return result;
    }
}
