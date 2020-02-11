package com.shul.rabbitmqserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;

@SpringBootApplication(exclude={
        JpaRepositoriesAutoConfiguration.class //禁止springboot自动加载持久化bean
})
public class RabbitmqserverApplication {

    public static void main(String[] args) {
        SpringApplication.run(RabbitmqserverApplication.class, args);
    }

}
