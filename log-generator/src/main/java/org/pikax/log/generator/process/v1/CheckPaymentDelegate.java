package org.pikax.log.generator.process.v1;

import org.activiti.engine.delegate.DelegateExecution;
import org.pikax.log.generator.AbstractSleepableServiceTaskDelegate;
import org.springframework.stereotype.Component;

@Component("checkPaymentDelegate")
public class CheckPaymentDelegate extends AbstractSleepableServiceTaskDelegate {

	@Override
	public void doExecute(final DelegateExecution execution) throws Exception {
		final double seed = Math.random();
		if (seed < 0.2) {
			execution.setVariable("payment", "notok");
		} else {
			execution.setVariable("payment", "ok");
		}
	}

	@Override
	protected int getMaximumDelay() {
		return 1000;
	}

}
