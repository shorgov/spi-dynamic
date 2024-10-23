package com.shorgov.spi;

import com.shorgov.spi.api.Worker;

public class AdvancedWorker implements Worker{
    @Override
    public String getId() {
        return "ADVANCED";
    }

    @Override
    public String doWork() {
        return "This is work done by an ADVANCED worker.";
    }
}
