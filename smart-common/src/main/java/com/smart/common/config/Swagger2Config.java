package com.smart.common.config;

import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LErry.li
 * Description:
 * swagger配置
 * Date: 2019/7/23 12:56
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {

    @Bean
    public Docket createRestApi() {
        ParameterBuilder tokenParam = new ParameterBuilder();
        List<Parameter> parameters = new ArrayList<>();
        tokenParam.name("Authorization")
                .defaultValue("token")
                .description("token令牌")
                .modelRef(new ModelRef("string"))
                .parameterType("header")
                .required(true)
                .build();
        parameters.add(tokenParam.build());

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(this.apiInfo())
                .globalOperationParameters(parameters)
                .select()
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Smart Cloud API 文档")
                .description("Smart Cloud 接口文档说明")
                .contact(new Contact("Lerry", "https://github.com/lerry903/smart-cloud", "lerry903@163.com"))
                .version("1.0.0")
                .build();
    }
}
