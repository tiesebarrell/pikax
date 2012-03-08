package org.pikax.log.generator;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;

public abstract class AbstractSleepableServiceTaskDelegate implements JavaDelegate {

	protected static final String VARIABLE_NAME_MAXIMUM_DELAY = "maximumDelay";

	public void execute(final DelegateExecution execution) throws Exception {

		final long delay = Math.round(Math.random() * getMaximumDelay());
		System.out.println(String.format("[Case %s] Sleeping thread for %s millis", getCaseId(execution), delay));
		Thread.sleep(delay);
		System.out.println(String.format("[Case %s] Resuming thread", getCaseId(execution)));

		doExecute(execution);
	}

	private String getCaseId(DelegateExecution execution) {
		return (String) execution.getVariable("id");
	}

	protected abstract int getMaximumDelay();

	protected abstract void doExecute(DelegateExecution execution) throws Exception;

}
