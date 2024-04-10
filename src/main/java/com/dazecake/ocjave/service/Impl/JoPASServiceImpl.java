package com.dazecake.ocjave.service.Impl;

import com.dazecake.ocjave.service.JoPASService;
import lombok.extern.slf4j.Slf4j;
import org.jopas.Jopas;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class JoPASServiceImpl implements JoPASService {

    @Value("${jopas.path:octave}")
    private String octavePath;

    private Jopas jopas;


    @Override
    public void init() {
        jopas = new Jopas(octavePath);
    }

    @Override
    public void destroy() {
        jopas.stop();
    }

    @Override
    public void run(String scriptFilePath) {
        try {
            jopas.Execute("run('" + scriptFilePath + "')");
        } catch (Exception e) {
            log.error("Error running script: " + scriptFilePath + "\n\t", e);
        }
    }


}
