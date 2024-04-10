package com.dazecake.ocjave.utils;

import com.dazecake.ocjave.service.JoPASService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class StopMethods implements DisposableBean {

    @Resource
    private JoPASService joPASService;

    @Override
    public void destroy() throws Exception {
        log.info("Stopping Ocjave...");
        joPASService.destroy();
        log.info("Ocjave stopped.");
    }
}
