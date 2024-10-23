package com.shorgov.spi.api;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;
import java.util.Map;
import java.util.ServiceLoader;
import java.util.ServiceLoader.Provider;
import java.util.stream.Collectors;

public class WorkerService {
    private static final Map<String, Worker> workers = new HashMap<>();
    private static final Map<String, URLClassLoader> workersLoaders = new HashMap<>();//may not be needed as file can be replaced but not renamed

    public static void load(String name) {
        try {
            URLClassLoader u = new URLClassLoader(new URL[]{new File(name).toURI().toURL()}, WorkerService.class.getClassLoader());
            ServiceLoader<Worker> sl = ServiceLoader.load(Worker.class, u);

            sl.stream().map(Provider::get).forEach(f -> {
                workers.put(f.getId(), f);
                workersLoaders.put(f.getId(), u);//or even close the classloader
            });

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public static void unloadAll() {
        workers.clear();
        workersLoaders.values().forEach(t -> {
            try {
                t.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        workersLoaders.clear();
    }

    public static String triggerWork() {
        if (workers.isEmpty()) {
            return "No workers loaded";
        } else {
            return workers.values().stream().map(Worker::doWork).collect(Collectors.joining("\n"));
        }
    }
}
