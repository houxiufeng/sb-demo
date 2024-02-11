package com.example.demo.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MyListener2 {

    @EventListener
    public void listener(MyEvent event) {

        log.info("MyListener2 listener " + event.getSource());
    }
}
