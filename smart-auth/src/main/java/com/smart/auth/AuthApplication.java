package com.smart.auth;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author LErry.li
 * Description:
 *	认证中心启动类
 * Date: 2019-7-22 18:44:35
 */
@ComponentScan({"com.smart.common", "com.smart.auth"})
@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication(exclude = DruidDataSourceAutoConfigure.class)
public class AuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthApplication.class, args);
	}
}
