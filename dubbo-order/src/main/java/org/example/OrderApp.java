package org.example;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hello world!
 *
 */
@EnableDubbo
@SpringBootApplication
public class OrderApp
{
    public static void main( String[] args )
    {
        SpringApplication.run(OrderApp.class, args);
    }
}
