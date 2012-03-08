package org.pikax.log.generator.process.v1;

import org.activiti.engine.delegate.DelegateExecution;
import org.pikax.log.generator.AbstractSleepableServiceTaskDelegate;
import org.springframework.stereotype.Component;

@Component("processClaimDelegate")
public class ProcessClaimDelegate extends AbstractSleepableServiceTaskDelegate {

	@Override
	public void doExecute(final DelegateExecution execution) throws Exception {
		final double seed = Math.random();
		if (seed < 0.2) {
			execution.setVariable("claim", "rejected");
		} else if (seed < 0.72) {
			execution.setVariable("claim", "ok");
		} else {
			execution.setVariable("claim", "behind");
		}
	}

	@Override
	protected int getMaximumDelay() {
		return 2500;
	}

}
