package com.testinium;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.apache.logging.log4j.Logger;

import org.apache.logging.log4j.LogManager;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        logger.info("Running tests...");
        Result result = JUnitCore.runClasses(TestAll.class);

        for (Failure failure : result.getFailures()) {
            logger.error("Failed test:");
            logger.error(failure.toString());
            System.out.println(failure.toString());
        }

        if (result.wasSuccessful()) {
            logger.info("All tests were successfull.");
            System.out.println(result.wasSuccessful());
        }
    }
}
