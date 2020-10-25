package test.boot.spring.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import test.boot.spring.ScheduledTask;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class PrivateLogger {
    private static final Logger logger = LoggerFactory.getLogger(ScheduledTask.class);
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    public void log(String message){
        logger.info(("***" + dateTimeFormatter.format(LocalDateTime.now())) + " : " + message);
    }

}
