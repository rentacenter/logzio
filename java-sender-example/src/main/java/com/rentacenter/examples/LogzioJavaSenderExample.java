package com.rentacenter.examples;

import io.logz.sender.LogzioSender;
import io.logz.sender.SenderStatusReporter;
import io.logz.sender.com.google.gson.JsonObject;
import io.logz.sender.com.google.common.util.concurrent.ThreadFactoryBuilder;
import io.logz.sender.exceptions.LogzioParameterErrorException;

import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Map;
import java.util.HashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class LogzioJavaSenderExample {
    public static void main(String[] args) {
        ScheduledExecutorService scheduledExecutorService;
        LogzioSender logzioSender;

        String logzioToken = System.getenv("LOGZIO_TOKEN");
        String logzioType = "java";
        int drainTimeoutSec = 5;
        int fileSystemFullPercentThreshold = 98;
        File bufferDirFile = new File(System.getProperty("java.io.tmpdir") + File.separator + logzioType, logzioType);
        String logzioUrl = null;
        int connectTimeout = 10 * 1000;
        int socketTimeout = 10 * 1000;
        boolean debug = true;
        boolean addHostname = false;
        int gcPersistedQueueFilesIntervalSeconds = 30;

        Map<String, String> additionalFieldsMap = new HashMap<>();

        try {
            scheduledExecutorService = Executors.newScheduledThreadPool(2, new ThreadFactoryBuilder().setDaemon(true).build());
            logzioSender = LogzioSender.getOrCreateSenderByType(logzioToken, logzioType, drainTimeoutSec, fileSystemFullPercentThreshold,
                bufferDirFile, logzioUrl, socketTimeout, connectTimeout, debug,
                new StatusReporter(), scheduledExecutorService, gcPersistedQueueFilesIntervalSeconds);
            logzioSender.start();
        } catch (LogzioParameterErrorException e) {
            System.err.println("Some of the configuration parameters of logz.io is wrong: " + e.getMessage());
            e.printStackTrace();
            return;
        }

        // add fields that should be logged across all events
        try {
            String hostname = InetAddress.getLocalHost().getHostName();
            additionalFieldsMap.put("hostname", hostname);
        } catch (UnknownHostException e) {
            System.err.println("The configuration addHostName was specified but the host could not be resolved, thus the field 'hostname' will not be added");
            e.printStackTrace();
        }

        // all logs must include "environment" & "application"
        additionalFieldsMap.put("environment", System.getenv("ENVIRONMENT"));
        additionalFieldsMap.put("application", "javasenderexample");

        // any additional values
        additionalFieldsMap.put("team", "DevOps");
        additionalFieldsMap.put("project", "Logz.io examples");

        // log actual messages
        JsonObject logMessage = new JsonObject();
        additionalFieldsMap.forEach(logMessage::addProperty);
        logMessage.addProperty("message", "Testing logz.io!");
        logzioSender.send(logMessage);

        // log actual messages
        logMessage = new JsonObject();
        additionalFieldsMap.forEach(logMessage::addProperty);
        logMessage.addProperty("message", "Winter is coming");
        logzioSender.send(logMessage);

        // stop the sender
        logzioSender.stop();
        scheduledExecutorService.shutdownNow();
    }

    static class StatusReporter implements SenderStatusReporter {

        @Override
        public void error(String msg) {
            System.err.println("Error: " + msg);
        }

        @Override
        public void error(String msg, Throwable e) {
            System.err.println("Error: " + msg);
            e.printStackTrace();
        }

        @Override
        public void warning(String msg) {
            System.err.println("Warning: " + msg);
        }

        @Override
        public void warning(String msg, Throwable e) {
            System.err.println("Warning: " + msg);
            e.printStackTrace();
        }

        @Override
        public void info(String msg) {
            System.err.println("Info: " + msg);
        }

        @Override
        public void info(String msg, Throwable e) {
            System.err.println("Info: " + msg);
            e.printStackTrace();
        }
    }
}
