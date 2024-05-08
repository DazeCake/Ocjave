package com.dazecake.ocjave.service.Impl;

import com.dazecake.ocjave.service.JoPASService;
import com.dazecake.ocjave.service.ScriptService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
@Service
public class ScriptServiceImpl implements ScriptService {

    @Resource
    private JoPASService joPASService;

    @Override
    public void runRawScript(String rawScript) {
        var file = generateTempFile(rawScript);
        joPASService.run(file.getAbsolutePath());
    }

    @Override
    public void runScript(byte[] scriptFile) {
        try {
            var tempFile = File.createTempFile(String.valueOf(System.currentTimeMillis()), ".m");
            Files.write(Path.of(tempFile.toURI()), scriptFile);
            tempFile.deleteOnExit();
            joPASService.run(tempFile.getAbsolutePath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Generate a temp file and write the raw script to it
     * @param raw the raw script
     * @return the temp file
     */
    File generateTempFile(String raw) {
        // create a temp file
        File tempFile = null;
        try {
            tempFile = File.createTempFile(String.valueOf(System.currentTimeMillis()), ".m");
            Files.write(Path.of(tempFile.toURI()), raw.getBytes());
            tempFile.deleteOnExit();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tempFile;
    }
}
