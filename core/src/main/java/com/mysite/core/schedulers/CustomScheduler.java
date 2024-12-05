package com.mysite.core.schedulers;

import org.apache.sling.commons.scheduler.ScheduleOptions;
import org.apache.sling.commons.scheduler.Scheduler;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.metatype.annotations.Designate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(service = Runnable.class, immediate = true)
@Designate(ocd = SlingSchedulerConfiguration.class)
public class CustomScheduler implements Runnable {

    @Reference
    private Scheduler scheduler;

    private String customParameter;
    private int schedulerId;
    private static final Logger log = LoggerFactory.getLogger(CustomScheduler.class);

    @Activate
    protected void activate(SlingSchedulerConfiguration config) {
        schedulerId = config.schedulerName().hashCode();
        customParameter = config.customParameter();
    }

    @Modified
    protected void modified(SlingSchedulerConfiguration config) {
        removeScheduler();
        schedulerId = config.schedulerName().hashCode();
        addScheduler(config);
    }

    @Deactivate
    protected void deactivate(SlingSchedulerConfiguration config) {
        removeScheduler();
    }

    private void removeScheduler() {
        scheduler.unschedule(String.valueOf(schedulerId));
    }

    private void addScheduler(SlingSchedulerConfiguration config) {
        if (config.enabled()) {
            ScheduleOptions scheduleOptions = scheduler.EXPR(config.cronExpression());
            scheduleOptions.name(config.schedulerName());
            scheduleOptions.canRunConcurrently(false);
            scheduler.schedule(this, scheduleOptions);
            log.info("Scheduler added");
            
            //Run the scheduler atleast once then based on the CRON expression
            ScheduleOptions scheduleOptionNow = scheduler.NOW();
            scheduler.schedule(this, scheduleOptionNow);
        } else {
            log.info("Scheduler is disabled now");
        }
    }

    @Override
    public void run() {
        log.info("Custom Scheduler is now running using the passed custom parameter, custom Parameter {}",
                customParameter);
    }
}
