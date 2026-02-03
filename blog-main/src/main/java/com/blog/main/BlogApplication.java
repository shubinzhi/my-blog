package com.blog.main;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * 博客系统启动类
 *
 * @author blog
 */
@SpringBootApplication
@ComponentScan(basePackages = { "com.blog" })
@MapperScan("com.blog.main.**.mapper")
public class BlogApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogApplication.class, args);
        System.out.println("""

                ┌──────────────────────────────────────────────────────────┐
                │                                                          │
                │     🚀 My Blog 启动成功!                                  │
                │     📚 API 文档: http://localhost:8080/doc.html          │
                │                                                          │
                └──────────────────────────────────────────────────────────┘
                """);
    }
}
