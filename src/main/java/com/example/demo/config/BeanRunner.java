package com.example.demo.config;


import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import java.util.Arrays;

/**
 * order 只能保证同一种类型的顺序，比如 CommandLineRunner, 但是不能保证 ApplicationRunner 的顺序
 */
@Slf4j
@Configuration
public class BeanRunner {

    @Bean
    @Order(1)
    public CommandLineRunner runner1() {
        return args -> {
            log.info("BeanRunner CommandLineRunner runner1 " + Arrays.toString(args));
        };
    }

    @Bean
    @Order(2)
    public CommandLineRunner runner2() {
        return args -> {
            log.info("BeanRunner CommandLineRunner runner2 " + Arrays.toString(args));
        };
    }

    @Bean
    @Order(3)
    public ApplicationRunner runner3() {
        return args -> {
            log.info("BeanRunner ApplicationRunner runner3 " + args.getOptionNames());
        };
    }
}
