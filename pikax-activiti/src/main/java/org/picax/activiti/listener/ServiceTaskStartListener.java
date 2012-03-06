package org.picax.activiti.listener;

import java.util.Calendar;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.springframework.beans.factory.annotation.Autowired;

public class ServiceTaskStartListener implements ExecutionListener {

    @Autowired
    private transient EventService eventService;

    public void notify(final DelegateExecution execution) throws Exception {
        String activityName = "Unknown";
        if (execution instanceof ExecutionEntity) {
            final ExecutionEntity executionEntity = (ExecutionEntity) execution;
            activityName = (String) executionEntity.getActivity().getProperty("name");
        }
        eventService.sendAuditEvent(execution.getProcessInstanceId(), activityName, Calendar.getInstance(),
                Calendar.getInstance());
    }
}
