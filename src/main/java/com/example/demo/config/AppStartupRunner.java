package com.example.demo.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Slf4j
@Component
public class AppStartupRunner implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) throws Exception {

        log.info("AppStartupRunner run: " + args.getOptionNames());
        log.info("AppStartupRunner run: " + args.getOptionValues("age"));
        log.info("AppStartupRunner run: " + Arrays.toString(args.getSourceArgs()));
    }
}
