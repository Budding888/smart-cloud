package com.smart.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LErry.li
 * Description:
 * <p>
 * Date: 2019/7/25 21:11
 */
@Data
@RefreshScope
@ConfigurationProperties(prefix = "ignore")
@Component
public class IgnoreUrlConfig {

    private List<String> urls = new ArrayList<>();

    private List<String> client = new ArrayList<>();

}
