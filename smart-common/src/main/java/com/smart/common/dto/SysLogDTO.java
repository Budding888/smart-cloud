package com.smart.common.dto;

import com.smart.common.constants.OperationStatusEnum;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author LErry.li
 * Description:
 * 日志信息
 * Date: 2019/7/22 20:21
 */
@Data
@Accessors(chain = true)
public class SysLogDTO implements Serializable {

    /**
     * 主键
     */
    private Long id;

    /**
     * 日志类型
     */
    private String type;

    /**
     * 模块名
     */
    private String moduleName;

    /**
     * 操作名
     */
    private String actionName;

    /**
     * 服务Key
     */
    private String serviceKey;

    /**
     * 操作IP地址
     */
    private String remoteAddr;

    /**
     * 用户代理
     */
    private String userAgent;

    /**
     * 请求URI
     */
    private String requestUri;

    /**
     * 操作方式
     */
    private String method;

    /**
     * 操作提交的数据
     */
    private String params;

    /**
     * 执行时间
     */
    private String time;

    /**
     * 异常信息
     */
    private String exception;

    /**
     * 删除标记
     */
    private String delFlag;

    /**
     * 创建者
     */
    private String createBy;

    /**
     * 操作状态
     *
     * @see OperationStatusEnum
     */
    private Integer status;

}
