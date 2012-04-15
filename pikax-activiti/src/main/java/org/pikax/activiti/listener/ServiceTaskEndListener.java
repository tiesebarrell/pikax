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
package org.pikax.activiti.listener;

import java.util.Calendar;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.pikax.core.PikaxProcessor;
import org.pikax.core.event.Event;
import org.pikax.core.event.ProcessLongRunningActivityEndedEvent;
import org.springframework.beans.factory.annotation.Autowired;

public class ServiceTaskEndListener implements ExecutionListener {
	
	@Autowired
	private transient PikaxProcessor pikaxProcessor;

	public void notify(final DelegateExecution execution) throws Exception {
		String activityName = "Unknown";
		if (execution instanceof ExecutionEntity) {
			final ExecutionEntity executionEntity = (ExecutionEntity) execution;
			activityName = (String) executionEntity.getActivity().getProperty("name");
		}

		final Event event = new ProcessLongRunningActivityEndedEvent(execution.getProcessInstanceId(), activityName, Calendar
				.getInstance());
		
		pikaxProcessor.processEvent(event);
	}
}
