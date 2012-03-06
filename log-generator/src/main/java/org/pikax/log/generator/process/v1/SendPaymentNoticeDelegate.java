package org.pikax.log.generator.process.v1;

import java.util.Calendar;

import org.activiti.engine.delegate.DelegateExecution;
import org.pikax.log.generator.AbstractAuditableDelegate;
import org.springframework.stereotype.Component;


@Component("sendPaymentNoticeDelegate")
public class SendPaymentNoticeDelegate extends AbstractAuditableDelegate {

    @Override
    protected String getAuditActivityName() {
        return "Send behind on payment notice";
    }

    @Override
    protected Calendar getStart(final DelegateExecution execution) {
        return getStartNow(execution);
    }

    @Override
    protected Calendar getEnd(final DelegateExecution execution) {
        return getEndForRandomDuration(execution, 60000, 180000);
    }

}
