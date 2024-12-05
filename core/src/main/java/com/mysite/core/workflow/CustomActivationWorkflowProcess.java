package com.mysite.core.workflow;

import com.adobe.granite.workflow.WorkflowException;
import com.adobe.granite.workflow.exec.WorkItem;
import com.adobe.granite.workflow.metadata.MetaDataMap;
import com.adobe.granite.workflow.exec.WorkflowData;
import com.adobe.granite.workflow.exec.WorkflowProcess;
import org.osgi.service.component.annotations.Component;
import org.osgi.framework.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.day.cq.replication.Replicator;

import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;

import java.util.Collections;

import javax.jcr.Session;

@Component(
    service = WorkflowProcess.class,
    property = {
        Constants.SERVICE_DESCRIPTION + "=Custom Workflow Step for Page Activation",
        "process.label=Custom Page Activation Process"
    }
)
public class CustomActivationWorkflowProcess implements WorkflowProcess {

    private static final Logger LOG = LoggerFactory.getLogger(CustomActivationWorkflowProcess.class);

    @org.osgi.service.component.annotations.Reference
    private Replicator replicator;

    @org.osgi.service.component.annotations.Reference
    private ResourceResolverFactory resourceResolverFactory;

    @Override
    public void execute(WorkItem workItem, com.adobe.granite.workflow.WorkflowSession workflowSession, MetaDataMap metaData) throws WorkflowException {
        WorkflowData workflowData = workItem.getWorkflowData();
        if ("JCR_PATH".equals(workflowData.getPayloadType())) {
            String pagePath = workflowData.getPayload().toString();
            LOG.info("Activating page: {}", pagePath);

            try (ResourceResolver resourceResolver = resourceResolverFactory.getServiceResourceResolver(
                Collections.singletonMap(ResourceResolverFactory.SUBSERVICE, "workflowService"))) {

                Session session = resourceResolver.adaptTo(Session.class);
                if (session != null) {
                    replicator.replicate(session, com.day.cq.replication.ReplicationActionType.ACTIVATE, pagePath);
                    LOG.info("Page successfully activated: {}", pagePath);
                }
            } catch (Exception e) {
                LOG.error("Error during page activation", e);
            }
        }
    }
}