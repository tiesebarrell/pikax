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

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.UUID;

/**
 * @author Tiese Barrell
 * 
 */
public abstract class AbstractSingleDateLogEvent implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -110237966471865683L;

	private static final String CORRELATION_ID_PATTERN = "%s.%s";

	private final String id;

	private final String caseId;
	private final Calendar momentInTime;
	private final String auditActivity;

	protected AbstractSingleDateLogEvent(final String caseId, final String auditActivity, final Calendar momentInTime) {
		super();
		this.id = UUID.randomUUID().toString();
		this.caseId = caseId;
		this.momentInTime = momentInTime;
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
	public String getMomentInTimeAsString() {
		return new SimpleDateFormat(Constants.TIMESTAMP_DATE_PATTERN).format(momentInTime.getTime());
	}

	/**
	 * @return the momentInTime
	 */
	public Calendar getMomentInTime() {
		return momentInTime;
	}

	public String getCorrelationId() {
		return String.format(CORRELATION_ID_PATTERN, caseId, auditActivity);
	}

	/**
	 * @return the auditActivity
	 */
	public String getAuditActivity() {
		return auditActivity;
	}

	public int compareTo(final AbstractSingleDateLogEvent o) {
		int result = 0;
		if (o != null) {
			result = momentInTime.compareTo(o.momentInTime);
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
				.append("\n").append("Moment in time: ").append(momentInTime);

		return builder.toString();
	}

}
