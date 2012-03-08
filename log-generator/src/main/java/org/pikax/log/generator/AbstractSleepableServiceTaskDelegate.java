/*******************************************************************************
 * Copyright 2012 Tiese Barrell
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
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
