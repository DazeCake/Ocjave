package com.dazecake.ocjave.service;

public interface JoPASService {

    /**
     * init jopas object
     */
    void init();

    /**
     * destroy jopas object
     */
    void destroy();

    /**
     * run script file
     * @param scriptFilePath script file path
     */
    void run(String scriptFilePath);
}
