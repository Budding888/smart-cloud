package com.smart.auth.exception;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.smart.common.constants.LogTypeEnum;
import com.smart.common.constants.MqQueueNameConstant;
import com.smart.common.constants.OperationStatusEnum;
import com.smart.common.constants.SmartServiceNameConstants;
import com.smart.common.dto.SysLogDTO;
import com.smart.common.util.ExceptionUtil;
import com.smart.common.util.IpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;

/**
 * @author LErry.li
 * Description:
 * oauth2异常处理器
 * Date: 2019/7/26 10:32
 */
@Slf4j
@Component("oAuth2WebResponseExceptionTranslator")
public class OAuth2WebResponseExceptionTranslator implements WebResponseExceptionTranslator<OAuth2Exception> {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public ResponseEntity<OAuth2Exception> translate(Exception e){
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        String username = request.getParameter("username");
        SysLogDTO sysLogDTO = new SysLogDTO()
                .setCreateBy(username)
                .setRequestUri(request.getRequestURI())
                .setUserAgent(request.getHeader("user-agent"))
                .setType(LogTypeEnum.LOGIN.code())
                .setStatus(OperationStatusEnum.FAIL.code())
                .setModuleName("auth认证模块")
                .setActionName("登录")
                .setServiceKey(SmartServiceNameConstants.SMART_AUTH)
                .setRemoteAddr(IpUtil.getRealIp(request))
                .setMethod(request.getMethod())
                .setException(ExceptionUtil.getTrace(e));
        rabbitTemplate.convertAndSend(MqQueueNameConstant.SYS_LOG_QUEUE, sysLogDTO);
        log.error(Arrays.toString(e.getStackTrace()));
        if (!(e instanceof OAuth2Exception)) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new SmartOauth2Exception(e.getMessage()));
        }
        OAuth2Exception oAuth2Exception = (OAuth2Exception) e;
        SmartOauth2Exception t = new SmartOauth2Exception(oAuth2Exception.getMessage());
        return ResponseEntity
                .status(HttpStatus.NOT_ACCEPTABLE)
                .body(t);
    }


    /**
     * oauth2自定义异常
     */
    @JsonSerialize(using = SmartOauthExceptionSerializer.class)
    class SmartOauth2Exception extends OAuth2Exception {
        SmartOauth2Exception(String msg) {
            super(msg);
        }
    }

    /**
     * 异常json序列化方式
     */
    class SmartOauthExceptionSerializer extends StdSerializer<SmartOauth2Exception> {
        protected SmartOauthExceptionSerializer() {
            super(SmartOauth2Exception.class);
        }

        @Override
        public  void serialize(SmartOauth2Exception value, JsonGenerator gen, SerializerProvider serializerProvider) throws IOException {
            gen.writeStartObject();
            gen.writeStringField("code", String.valueOf(value.getHttpErrorCode()));
            gen.writeStringField("message", "用户名或密码错误");
            gen.writeStringField("data", null);
            if (value.getAdditionalInformation()!=null) {
                for (Map.Entry<String, String> entry : value.getAdditionalInformation().entrySet()) {
                    String key = entry.getKey();
                    String add = entry.getValue();
                    gen.writeStringField(key, add);
                }
            }
            gen.writeEndObject();
        }
    }
}
