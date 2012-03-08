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
