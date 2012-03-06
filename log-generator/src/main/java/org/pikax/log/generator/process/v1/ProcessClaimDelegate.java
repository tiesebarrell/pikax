package org.pikax.log.generator.process.v1;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component("processClaimDelegate")
public class ProcessClaimDelegate implements JavaDelegate {

    /**
     * {@inheritDoc}
     * 
     * @see org.pikax.log.generator.AbstractAuditableDelegate#execute(org.activiti.engine.delegate.DelegateExecution)
     */
    public void execute(final DelegateExecution execution) throws Exception {

        final double seed = Math.random();
        if (seed < 0.2) {
            execution.setVariable("claim", "rejected");
        } else if (seed < 0.72) {
            execution.setVariable("claim", "ok");
        } else {
            execution.setVariable("claim", "behind");
        }
    }

    // @Override
    // protected String getAuditActivityName() {
    // return "Process claim";
    // }
    //
    // @Override
    // protected Calendar getStart(final DelegateExecution execution) {
    // return getStartNow(execution);
    // }
    //
    // @Override
    // protected Calendar getEnd(final DelegateExecution execution) {
    // return getEndForRandomDuration(execution, 60000, 180000);
    // }

}
