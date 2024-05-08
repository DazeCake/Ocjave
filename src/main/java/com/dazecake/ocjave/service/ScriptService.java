package com.dazecake.ocjave.service;

public interface ScriptService {

    /**
     * Run a raw script
     * @param rawScript the raw script code
     */
    void runRawScript(String rawScript);

    /**
     * Run a script file
     * @param scriptFile the script file
     */
    void runScript(byte[] scriptFile);
}
