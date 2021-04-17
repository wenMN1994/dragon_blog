package com.dragon.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;


/**
 * @author chenshun
 */
@EnableFeignClients
@EnableDiscoveryClient
@ComponentScan("com.dragon")
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class BlogAdminApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlogAdminApplication.class, args);
	}

}