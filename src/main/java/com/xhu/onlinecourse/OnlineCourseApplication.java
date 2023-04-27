package com.xhu.onlinecourse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/*
主类，启动springboot
 */
@SpringBootApplication
//@EnableWebMvc//异常处理
public class OnlineCourseApplication {
    public static void main(String[] args) {
        SpringApplication.run(OnlineCourseApplication.class, args);
    }
}
