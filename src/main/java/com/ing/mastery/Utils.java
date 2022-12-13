package com.ing.mastery;

import ch.qos.logback.classic.Level;
import org.slf4j.LoggerFactory;

public class Utils {

    public static void rootLogInfo() {
        var logger = (ch.qos.logback.classic.Logger) LoggerFactory.getLogger(ch.qos.logback.classic.Logger.ROOT_LOGGER_NAME);
        logger.setLevel(Level.INFO);
    }
}
