package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hello world!
 *
 */
@SpringBootApplication
public class BigEventApplication 
{
    public static void main( String[] args )
    {
        SpringApplication.run(BigEventApplication.class, args);
    }
}

/*
 * 
 * 开发接口的流程：
 * 1. 明确需求->用户如何使用功能
 * 2. 阅读接口文档->明确接口输入和输出
 * 3. 思路分析->分析代码逻辑
 * 4. 开发
 * 5. 测试
 */