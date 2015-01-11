package adobe.ags.datalayer.core.impl;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;
import org.apache.sling.settings.SlingSettingsService;

import adobe.ags.datalayer.core.HelloService;

/**
 * One implementation of the {@link HelloService}. Note that
 * the settings service is injected, not retrieved.
 */
@Service(value = HelloService.class)
@Component
public class HelloServiceImpl implements HelloService {

	@Reference
	private SlingSettingsService settings;
	
	@Override
	public String getMessage() {
		return "Hello World, this is a cool instance but needs work OMG very cool " + settings.getSlingId();
	}

}
