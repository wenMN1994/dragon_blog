package com.dragon;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * @author：Dragon Wen
 * @email：18475536452@163.com
 * @date：Created in 2019/3/18 17:09
 * @description：${description}
 * @modified By：
 * @version: 1.0.0
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@MapperScan("com.dragon.project.**.mapper")
public class MyBlogApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(MyBlogApplication.class, args);
	}

}
