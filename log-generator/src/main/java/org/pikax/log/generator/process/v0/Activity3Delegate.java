package org.pikax.log.generator.process.v0;

import java.util.Calendar;

import org.activiti.engine.delegate.DelegateExecution;
import org.pikax.log.generator.AbstractAuditableDelegate;
import org.springframework.stereotype.Component;


@Component("activity3Delegate")
public class Activity3Delegate extends AbstractAuditableDelegate {

    @Override
    protected String getAuditActivityName() {
        return "Activity 3";
    }

    @Override
    protected Calendar getStart(final DelegateExecution execution) {
        return getStartNow(execution);
    }

    @Override
    protected Calendar getEnd(final DelegateExecution execution) {
        return getEndNow(execution);
    }

}
