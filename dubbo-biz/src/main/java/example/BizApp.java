package example;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hello world!
 *
 */
@EnableDubbo
@SpringBootApplication
public class BizApp {
    public static void main( String[] args ) {
        SpringApplication.run(BizApp.class, args);
    }
}
