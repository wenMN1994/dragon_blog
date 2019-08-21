package com.dragon.framework.config;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author：Dragon Wen
 * @email：18475536452@163.com
 * @date：Created in 2019/7/17 21:39
 * @description： Swagger2的接口配置
 * @modified By：
 * @version: 1.0.0
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    /**
     * 系统基础配置
     */
    @Autowired
    private SystemConfig systemConfig;

    /**
     * 创建API
     */
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                // 详细定制
                .apiInfo(apiInfo())
                .select()
                // 指定当前包路径
                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                // 扫描所有 .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * 添加摘要信息
     */
    private ApiInfo apiInfo() {
        // 用ApiInfoBuilder进行定制
        return new ApiInfoBuilder()
                .title("DragonWen个人博客系统")
                .description("温文星的个人博客系统")
                .contact(new Contact(systemConfig.getName(), "http://dragonwen.wicp.vip/", "18475536452@163.com"))
                .version("版本号:" + systemConfig.getVersion())
                .build();
    }
}
