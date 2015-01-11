package adobe.ags.datalayer.core.impl.schedulers;

import java.util.Map;

import org.apache.felix.scr.annotations.Activate;
import org.apache.felix.scr.annotations.Modified;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Properties;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Service;
import org.apache.sling.commons.osgi.PropertiesUtil;
import org.osgi.service.component.ComponentContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A simple demo for cron-job like tasks that get executed regularly.
 * It also demonstrates how property values can be set. Users can
 * set the property values in /system/console/configMgr
 */
@Component(metatype = true, label = "A scheduled task")
@Service(value = Runnable.class)
@Properties({
    @Property(name = "scheduler.expression", value = "*/30 * * * * ?"),
    @Property(name = "scheduler.concurrent", boolValue=false)    
})
public class SimpleScheduledTask implements Runnable {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Override
    public void run() {
        logger.debug("SimpleScheduledTask is now running, myParameter='{}'", this.myParameter);
    }
    
    @Property(label = "A parameter", description = "Can be configured in /system/console/configMgr")
    public static final String MY_PARAMETER = "myParameter";
    private String myParameter;
    
    @Activate
    protected void activate(final Map<String, Object> config) {
        configure(config);
    }

    private void configure(final Map<String, Object> config) {
        this.myParameter = PropertiesUtil.toString(config.get(MY_PARAMETER), null);
        logger.debug("configure: myParameter='{}''", this.myParameter);
    }
}
