package com.mysite.core.listeners;

import org.apache.sling.api.resource.ModifiableValueMap;
import org.apache.sling.api.resource.PersistenceException;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventConstants;
import org.osgi.service.event.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.replication.ReplicationAction;
import com.mysite.core.services.NPUtilService;

@Component(
    service = EventHandler.class,
    property = {
        //"event.topics = com/day/cq/replication",
        EventConstants.EVENT_TOPIC + "=" + ReplicationAction.EVENT_TOPIC,
        EventConstants.EVENT_FILTER + "= (& (type=ACTIVATE) (paths=/content/mysite/us/*))"
    },
    immediate = true
)
public class ArticleActivationHandler implements EventHandler{
 
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Reference
    NPUtilService npUtilService;

    @Override
    public void handleEvent(Event event) {
        String [] paths = (String[]) event.getProperty("paths");
        ResourceResolver resolver = npUtilService.getResourceResolver();
        for(String path : paths) {
            Resource contentRes = resolver.getResource(path + "/jcr:content");
            ModifiableValueMap mProp = contentRes.adaptTo(ModifiableValueMap.class);
            mProp.put("pageActivation", true);
        }
        try {
            resolver.commit();
        } catch(PersistenceException e){
            throw new RuntimeException(e);
        }
        logger.info("Inside Handle Event Method()" + event.getTopic());
    }
}
