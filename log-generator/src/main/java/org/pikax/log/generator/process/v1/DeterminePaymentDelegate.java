package org.pikax.log.generator.process.v1;

import org.activiti.engine.delegate.DelegateExecution;
import org.pikax.log.generator.AbstractSleepableServiceTaskDelegate;
import org.springframework.stereotype.Component;

@Component("determinePaymentDelegate")
public class DeterminePaymentDelegate extends AbstractSleepableServiceTaskDelegate {

	@Override
	public void doExecute(final DelegateExecution execution) throws Exception {

	}

	@Override
	protected int getMaximumDelay() {
		return 2000;
	}

}
