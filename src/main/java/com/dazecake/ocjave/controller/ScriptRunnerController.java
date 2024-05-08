package com.dazecake.ocjave.controller;

import com.dazecake.ocjave.modle.vo.request.RawScriptVO;
import com.dazecake.ocjave.service.ScriptService;
import com.dazecake.ocjave.utils.Result;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "ScriptRunnerInterface", description = "Load and run scripts")
@RequestMapping("/api")
@RestController
public class ScriptRunnerController {

    @Resource
    private ScriptService scriptService;

    @PostMapping("/runRawScript")
    public Result<Void> runRawScript(@RequestBody RawScriptVO rawScriptVO) {
        scriptService.runRawScript(rawScriptVO.getRawScript());
        return Result.success("Script executed successfully");
    }

    @PostMapping("/runScript")
    public Result<Void> runScript(byte[] scriptFile) {
        scriptService.runScript(scriptFile);
        return Result.success("Script executed successfully");
    }
}
