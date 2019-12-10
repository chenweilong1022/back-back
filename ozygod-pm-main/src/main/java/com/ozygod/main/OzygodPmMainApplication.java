package com.ozygod.main;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@EnableSwagger2Doc
@SpringBootApplication
@ComponentScan(basePackages = "com.ozygod")
//启用切面
@EnableAspectJAutoProxy
public class OzygodPmMainApplication {

	public static void main(String[] args) {
		SpringApplication.run(OzygodPmMainApplication.class, args);


	}

	/**
	 * 时区设置为东八区
	 */
	@PostConstruct
	void setDefaultTimezone() {
		TimeZone.setDefault(TimeZone.getTimeZone("Asia/Shanghai"));
	}
}
