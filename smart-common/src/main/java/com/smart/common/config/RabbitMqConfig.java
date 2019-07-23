package com.smart.common.config;

import com.smart.common.constants.MqQueueNameConstant;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author LErry.li
 * Description:
 * rabbitmq配置
 * Date: 2019/7/23 14:20
 */
@Configuration
public class RabbitMqConfig {

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(new Jackson2JsonMessageConverter());
        return template;
    }

    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(new Jackson2JsonMessageConverter());
        return factory;
    }

    /**
     * 获取系统日志队列
     *
     * @return 系统日志队列
     */
    @Bean
    public Queue sysLogQueue() {
        return new Queue(MqQueueNameConstant.SYS_LOG_QUEUE);
    }

    /**
     * 获取短信验证码队列
     *
     * @return 短信验证码队列
     */
    @Bean
    public Queue mobileCodeQueue() {
        return new Queue(MqQueueNameConstant.MOBILE_CODE_QUEUE);
    }

    /**
     * 获取邮件队列
     *
     * @return 邮件队列
     */
    @Bean
    public Queue mailCodeQueue() {
        return new Queue(MqQueueNameConstant.MAIL_CODE_QUEUE);
    }

}
