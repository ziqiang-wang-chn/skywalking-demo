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
public class UserApp
{
    public static void main( String[] args )
    {
        SpringApplication.run(UserApp.class, args);
    }
}
