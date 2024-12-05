package com.mysite.core.schedulers;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.AttributeType;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(
        name = "SlingSchedulerConfiguration",
        description = "Sling scheduler configuration")
public @interface SlingSchedulerConfiguration {
    @AttributeDefinition(name = "Scheduler name", description = "Name of the scheduler", type = AttributeType.STRING)
    public String schedulerName() default "Custom Sling Scheduler Configuration";

    @AttributeDefinition(name = "Enabled", description = "True, if scheduler service is enabled", type = AttributeType.BOOLEAN)
    public boolean enabled() default false;

    @AttributeDefinition(name = "Cron Expression", description = "Cron expression used by the scheduler", type = AttributeType.STRING)
    public String cronExpression() default "0/5 * * * * ?"; //runs every 5 seconds

    @AttributeDefinition(name = "Custom Parameter", description = "Custom parameter to be displayed by the scheduler", type = AttributeType.STRING)
    public String customParameter() default "AEM Scheduler Demo";

}
