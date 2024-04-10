package com.dazecake.ocjave.utils;

import com.dazecake.ocjave.service.JoPASService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class InitMethods implements ApplicationRunner {

    @Resource
    private JoPASService joPASService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("Loading JoPASService...");
        joPASService.init();
        log.info("JoPASService loaded.");
        log.info("OCJave has started.");
    }
}
