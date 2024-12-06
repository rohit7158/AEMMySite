package com.mysite.core.workflow;

import javax.jcr.Session;

import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.granite.workflow.WorkflowException;
import com.adobe.granite.workflow.WorkflowSession;
import com.adobe.granite.workflow.exec.WorkItem;
import com.adobe.granite.workflow.exec.WorkflowProcess;
import com.adobe.granite.workflow.metadata.MetaDataMap;
import com.day.cq.replication.ReplicationActionType;
import com.day.cq.replication.ReplicationException;
import com.day.cq.replication.Replicator;

@Component(
    service = WorkflowProcess.class,
    immediate = true,
    property = {
        "process.label=Custom Page Activation Process",
        Constants.SERVICE_DESCRIPTION + "=Custom Workflow Step for Page Activation",
    }
)
public class CustomWorkflowActivatePage implements WorkflowProcess {

    private static final Logger log = LoggerFactory.getLogger(CustomWorkflowActivatePage.class);
    
    @Reference
    private Replicator replicator;

    @Override
    public void execute(WorkItem workitem, WorkflowSession workflowSession, MetaDataMap args) throws WorkflowException {
        log.info("\n\n+++++++++++Custom Workflow +++++++++++++++++");
        Session session = workflowSession.adaptTo(Session.class);
        String payload = workitem.getWorkflowData().getPayload().toString();
        try {
            replicator.replicate(session, ReplicationActionType.ACTIVATE, payload);
            log.info("Page successfully activated: {}", payload);
        } catch (ReplicationException e) {
            log.error("Error during page activation", e);
            throw new RuntimeException(e);
        }
    }
}