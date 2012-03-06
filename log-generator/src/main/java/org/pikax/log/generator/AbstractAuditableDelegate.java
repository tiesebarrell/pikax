/**
 * 
 */
package org.pikax.log.generator;

import java.util.Calendar;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author tiesebarrell
 */
public abstract class AbstractAuditableDelegate implements JavaDelegate {

    private static final String VARIABLE_NAME_SIMULATED_TIME_DELAY = "simulatedTimeDelay";

    @Autowired
    private transient EventService eventService;

    /**
     * {@inheritDoc}
     * 
     * @see org.activiti.engine.delegate.JavaDelegate#execute(org.activiti.engine.delegate.DelegateExecution)
     */
    public void execute(final DelegateExecution execution) throws Exception {
        System.out.println(String.format("Executing %s", AbstractAuditableDelegate.class.getCanonicalName()));

        synchronized (execution) {
            final Calendar start = getStart(execution);
            final Calendar end = getEnd(execution);
            if (end.after(start)) {
                final int newDelay =
                        (int) (getSimulatedTimeDelay(execution) + (end.getTimeInMillis() - start.getTimeInMillis()));
                System.out.println(String.format("Setting new simulated delay to %s for case %s", newDelay,
                        execution.getVariable("id")));
                execution.setVariable(VARIABLE_NAME_SIMULATED_TIME_DELAY, newDelay);

            }
            eventService.sendAuditEvent(execution.getProcessInstanceId(), getAuditActivityName(), start, end);
        }
    }

    protected abstract String getAuditActivityName();

    protected abstract Calendar getStart(final DelegateExecution execution);

    protected abstract Calendar getEnd(final DelegateExecution execution);

    protected int getSimulatedTimeDelay(final DelegateExecution execution) {
        synchronized (execution) {
            int result = 0;
            final Object stored = execution.getVariable(VARIABLE_NAME_SIMULATED_TIME_DELAY);
            if (stored != null) {
                result = (Integer) stored;
            }
            return result;
        }
    }

    protected Calendar getStartNow(final DelegateExecution execution) {
        synchronized (execution) {
            final Calendar now = Calendar.getInstance();
            now.add(Calendar.MILLISECOND, getSimulatedTimeDelay(execution));
            return now;
        }
    }

    protected Calendar getEndNow(final DelegateExecution execution) {
        return getStartNow(execution);
    }

    protected Calendar getEndForRandomDuration(final DelegateExecution execution, final int minimum, final int maximum) {
        synchronized (execution) {
            final Calendar calendar = getEndNow(execution);
            final long randomDuration = Math.round(Math.random() * (maximum - minimum));
            calendar.add(Calendar.MILLISECOND, Integer.parseInt(Long.toString(randomDuration)));
            return calendar;
        }
    }
}
