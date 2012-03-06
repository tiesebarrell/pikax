package org.pikax.log.generator.process.v1;

import java.util.Calendar;

import org.activiti.engine.delegate.DelegateExecution;
import org.pikax.log.generator.AbstractAuditableDelegate;
import org.springframework.stereotype.Component;


@Component("checkPaymentDelegate")
public class CheckPaymentDelegate extends AbstractAuditableDelegate {

    /**
     * {@inheritDoc}
     * 
     * @see org.pikax.log.generator.AbstractAuditableDelegate#execute(org.activiti.engine.delegate.DelegateExecution)
     */
    @Override
    public void execute(final DelegateExecution execution) throws Exception {
        super.execute(execution);

        Thread.sleep(Math.round(Math.random() * 10000));

        final double seed = Math.random();
        if (seed < 0.6) {
            execution.setVariable("payment", "notok");
        } else {
            execution.setVariable("payment", "ok");
        }
    }

    @Override
    protected String getAuditActivityName() {
        return "Check payment";
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
