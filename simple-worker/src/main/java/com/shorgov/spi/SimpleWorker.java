package com.shorgov.spi;

import com.shorgov.spi.api.Worker;

public class SimpleWorker implements Worker {
    @Override
    public String getId() {
        return "SIMPLE";
    }

    @Override
    public String doWork() {
        return "This is work done by a SIMPLE worker.";
    }
}
