package ua.com.foxminded.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class LoggerController {
    public static final Logger LOG = LoggerFactory.getLogger(LoggerController.class);

    public String getMessage(){
        LOG.trace("trace message");
        LOG.debug("debug message");
        LOG.info("info message");
        LOG.warn("warn message");
        LOG.error("error message");

        return "Check console for printed logs";
    }
}
