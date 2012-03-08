package org.pikax.log.generator.process.v1;

import org.activiti.engine.delegate.DelegateExecution;
import org.pikax.log.generator.AbstractSleepableServiceTaskDelegate;
import org.springframework.stereotype.Component;

@Component("sendRejectionLetterDelegate")
public class SendRejectionLetterDelegate extends AbstractSleepableServiceTaskDelegate {

	@Override
	public void doExecute(final DelegateExecution execution) throws Exception {
	}

	@Override
	protected int getMaximumDelay() {
		return 5000;
	}

}