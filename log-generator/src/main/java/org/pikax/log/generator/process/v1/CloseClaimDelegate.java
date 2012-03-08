package org.pikax.log.generator.process.v1;

import org.activiti.engine.delegate.DelegateExecution;
import org.pikax.log.generator.AbstractSleepableServiceTaskDelegate;

public class CloseClaimDelegate extends AbstractSleepableServiceTaskDelegate {

	@Override
	public void doExecute(final DelegateExecution execution) throws Exception {
	}

	@Override
	protected int getMaximumDelay() {
		return 1000;
	}

}
