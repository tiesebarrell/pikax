package org.pikax.activiti.listener;

import java.util.Calendar;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.pikax.core.CsvFileWriter;
import org.pikax.core.LogStartEvent;

public class ServiceTaskStartListener implements ExecutionListener {

	public void notify(final DelegateExecution execution) throws Exception {
		String activityName = "Unknown";
		if (execution instanceof ExecutionEntity) {
			final ExecutionEntity executionEntity = (ExecutionEntity) execution;
			activityName = (String) executionEntity.getActivity().getProperty("name");
		}

		CsvFileWriter.writeEvent(new LogStartEvent(execution.getProcessInstanceId(), activityName, Calendar
				.getInstance()));
	}
}
