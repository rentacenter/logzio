package com.rentacenter.examples;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import org.apache.logging.log4j.core.LifeCycle;

public class LogzioLog4j2Example {
    public static void main(String[] args) {
        Logger logger = LogManager.getLogger(LogzioLog4j2Example.class);

        // programatic way to inject specific tags across all logger calls
        ThreadContext.put("team", "DevOps");
        ThreadContext.put("project", "Logz.io examples");

        // sample logs
        logger.info("Testing logz.io!");
        logger.warn("Winter is coming");

        // You can also use Markers to enrich log statements
        Marker marker = MarkerManager.getMarker("Fatal");
        logger.error(marker, "This line has a fatal error");

        // no reason to manually shutdown, Log4j 2 adds shutdown hook automatically
        //LogManager.shutdown();
    }
}
