package adobe.ags.datalayer.core.impl.listeners;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;
import org.apache.sling.api.SlingConstants;
import org.osgi.service.component.ComponentContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventConstants;
import org.osgi.service.event.EventHandler;

/**
 * A service to demonstrate how changes in the resource tree
 * can be listened for. It registers an event handler service.
 * The component is activated immediately after the bundle is
 * started through the immediate flag.
 * Please note, that apart from EventHandler services,
 * the immediate flag should not be set on a service.
 */
@Component(immediate = true)
@Service(value = EventHandler.class)
@Property(name = EventConstants.EVENT_TOPIC, value = "org/apache/sling/api/resource/Resource/*")
public class SimpleResourceListener implements EventHandler {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public void  handleEvent(final Event event) {
        logger.debug("Resource event: {} at: {}", event.getTopic(), event.getProperty(SlingConstants.PROPERTY_PATH));
    }
}

