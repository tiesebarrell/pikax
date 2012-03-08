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
/**
 * 
 */
package org.pikax.core;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.UUID;

/**
 * @author Tiese Barrell
 * 
 */
public class LogEvent {

	private final String id;

	private final String caseId;
	private final Calendar start;
	private Calendar end;
	private final String auditActivity;

	protected LogEvent(final String caseId, final String auditActivity, final AbstractSingleDateLogEvent start,
			final AbstractSingleDateLogEvent end) {
		super();
		this.id = UUID.randomUUID().toString();
		this.caseId = caseId;
		this.start = start.getMomentInTime();
		this.end = end.getMomentInTime();
		this.auditActivity = auditActivity;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @return the caseId
	 */
	public String getCaseId() {
		return caseId;
	}

	/**
	 * @return the start
	 */
	public String getStart() {
		return new SimpleDateFormat(Constants.TIMESTAMP_DATE_PATTERN).format(start.getTime());
	}

	/**
	 * @return the end
	 */
	public String getEnd() {
		return new SimpleDateFormat(Constants.TIMESTAMP_DATE_PATTERN).format(end.getTime());
	}

	protected void setEnd(Calendar end) {
		this.end = end;
	}

	/**
	 * @return the auditActivity
	 */
	public String getAuditActivity() {
		return auditActivity;
	}

	public int compareTo(final LogEvent o) {
		int result = 0;
		if (o != null) {
			result = start.compareTo(o.start);
		}
		return result;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append("Case: ").append(caseId).append("\n").append("Activity name: ").append(auditActivity)
				.append("\n").append("Start: ").append(start).append("\n").append("End: ").append(end);

		return builder.toString();
	}

}
