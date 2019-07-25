[![License](https://img.shields.io/github/license/lerry903/smart-cloud.svg)](https://github.com/lerry903/smart-cloud/blob/master/LICENSE)
[![GitHub Release](https://img.shields.io/github/release/lerry903/smart-cloud.svg)](https://github.com/lerry903/smart-cloud/releases)
[![Codecov](https://img.shields.io/codecov/c/github/lerry903/smart-cloud.svg)](https://codecov.io/gh/lerry903/smart-cloud)
[![build](https://travis-ci.org/lerry903/smart-cloud.svg?branch=master)](https://travis-ci.org/lerry903/smart-cloud.svg)
[![SpringBoot](https://img.shields.io/badge/SpringBoot-2.1.6.RELEASE-brightgreen.svg)](https://docs.spring.io/spring-boot/docs/2.1.6.RELEASE/reference/htmlsingle/)

# 简介
smart-cloud 是一个基于Spring Boot + Spring Cloud 的种子项目，用于快速构建中小型API、RESTful API项目,稳定、简单、快速使我们摆脱那些重复劳动，专注于业务代码的编写，减少加班。

# 源码托管
> **[Github](https://github.com/lerry903/smart-cloud)** | **[Gitee](https://gitee.com/lerry903/smart-cloud)**

# 技术栈
此项目是 Spring cloud Oauth2 构建的后台管理系统，计划采用以下技术
- 核心框架：Spring Boot + Spring Cloud Alibaba
- 注册中心：Nacos
- 配置中心：Nacos
- 服务网关：Spring cloud-Gateway
- 服务调用：Spring-cloud-open-Feign
- 负载均衡：Spring-cloud-loadbalancer
- 熔断降级：Sentinel
- 链路追踪：Skywalking
- 消息队列：RabbitMQ
- 权限认证：Spring secruity Oauth2
- ORM框架 ：MyBatis-Plus MyBatis增强工具
- Java工具: HuTool
- 项目部署：Docker+Rancher+K8S

# 应用架构

该项目包含以下服务

* smart-common - 公共模块
* smart-config - 外部配置
* smart-monitor - 监控
* smart-gateway - 代理所有微服务的接口网关
* smart-auth - OAuth2 认证服务
* smart-track - 分布式跟踪
 
# 启动项目


# 项目预览
## 注册中心
- Nacos控制台
    - 地址:http://nacos.lerry903.com/nacos
    - 账户与密码均为：nacos
    
## 监控中心

## RabbitMQ 监控

# 接口测试


## License

用户在遵循本项目协议的同时，如果用户下载、安装、使用本项目中所提供的软件，软件作者对任何原因在使用本项目中提供的软件时可能对用户自己或他人造成的任何形式的损失和伤害不承担任何责任。作者有权根据有关法律、法规的变化修改本项目协议。修改后的协议会随附于本项目的新版本中。当发生有关争议时，以最新的协议文本为准。如果用户不同意改动的内容，用户可以自行删除本项目。如果用户继续使用本项目，则视为您接受本协议的变动。

感谢大家 [Star](https://github.com/lerry903/smart-cloud/stargazers) & [Fork](https://github.com/lerry903/smart-cloud/network/members) 的支持。
