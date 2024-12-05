package com.mysite.core.workflow;

import com.day.cq.workflow.WorkflowException;
import com.day.cq.workflow.WorkflowSession;
import com.day.cq.workflow.exec.WorkItem;
import com.day.cq.workflow.exec.WorkflowProcess;
import com.day.cq.workflow.metadata.MetaDataMap;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(service = WorkflowProcess.class,
        immediate = true,
        property = {
                "process.label" + "= My custom Process Step",
                Constants.SERVICE_VENDOR + "=My Site",
                Constants.SERVICE_DESCRIPTION + "=My Custom site"
        }
)
public class CustomWorkflowStep implements WorkflowProcess{

    private static final Logger log = LoggerFactory.getLogger(CustomWorkflowStep.class);
    @Override
    public void execute(WorkItem workItem, WorkflowSession workflowSession, MetaDataMap metaDataMap) throws WorkflowException {
        log.info("\n\n+++++++++++Custom Workflow +++++++++++++++++");
    }
}
