package test.boot.spring;

import org.springframework.scheduling.annotation.Scheduled;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import test.boot.spring.utils.PrivateLogger;
import java.time.format.DateTimeFormatter;


@Component
public class ScheduledTask {

    @Autowired
    PrivateLogger privateLogger;

    @Scheduled(cron = "${scheduler.expression}")
    public void scheduleTaskWithCronExpression() {
        privateLogger.log("CRON executed");
    }
}
