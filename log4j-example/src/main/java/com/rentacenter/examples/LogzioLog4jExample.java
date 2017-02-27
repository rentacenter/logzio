package com.rentacenter.examples;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.MDC;

public class LogzioLog4jExample {
    public static void main(String[] args) {
        Logger logger = LogManager.getLogger(LogzioLog4jExample.class);

        // programatic way to inject specific tags across all logger calls
        MDC.put("team", "DevOps");
        MDC.put("project", "Logz.io examples");

        // sample logs
        logger.info("Testing logz.io!");
        logger.warn("Winter is coming");

        // to allow appender to flush queue & gracefully terminate
        LogManager.shutdown();
    }
}
