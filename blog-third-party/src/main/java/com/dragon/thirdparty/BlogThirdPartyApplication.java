package com.dragon.thirdparty;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author DragonWen
 */
@EnableDiscoveryClient
@SpringBootApplication(exclude = {DruidDataSourceAutoConfigure.class})
public class BlogThirdPartyApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogThirdPartyApplication.class, args);
    }

}
