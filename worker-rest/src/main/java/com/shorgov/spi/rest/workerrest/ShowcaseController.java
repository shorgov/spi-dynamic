package com.shorgov.spi.rest.workerrest;

import com.shorgov.spi.api.WorkerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShowcaseController {
    @GetMapping(value = "/trigger-work", produces = "text/plain")
    public String party() {
        return WorkerService.triggerWork();
    }

    @GetMapping(value = "/load-simple", produces = "text/plain")
    public String loadsimple() {
        WorkerService.load("./plugins/simple.jar");
        return "load-simple - executed";
    }

    @GetMapping(value = "/load-advanced", produces = "text/plain")
    public String loadadv() {
        WorkerService.load("./plugins/advanced.jar");
        return "load-advanced - executed";
    }

    @GetMapping(value = "/unload", produces = "text/plain")
    public String unload() {
        WorkerService.unloadAll();
        return "unload - executed";
    }
}
