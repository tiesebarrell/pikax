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
package org.pikax.core.event;

import java.io.Serializable;
import java.util.Calendar;
import java.util.UUID;

/**
 * Base implementation for {@link Event}s that pertain to processes.
 * 
 * @author Tiese Barrell
 * 
 */
public abstract class AbstractProcessEvent implements Event, Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7986057729783522987L;

	private static final String CORRELATION_ID_PATTERN = "%s.%s";

	private final String id;
	private final String caseId;
	private final Calendar occurred;
	private final String name;

	protected AbstractProcessEvent(final String caseId, final String name, final Calendar occurred) {
		super();
		this.id = UUID.randomUUID().toString();
		this.caseId = caseId;
		this.occurred = occurred;
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public String getCaseId() {
		return caseId;
	}
	
	public Calendar getOccurred() {
		return occurred;
	}

	public String getName() {
		return name;
	}
	
	public String getCorrelationId() {
		return String.format(CORRELATION_ID_PATTERN, caseId, name);
	}

	public int compareTo(final AbstractProcessEvent o) {
		int result = 0;
		if (o != null) {
			result = id.compareTo(o.id);
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
		builder.append("ID: ").append(id).append("\n").append("Case: ").append(caseId).append("\n").append("Name: ").append(name)
				.append("\n").append("Occurred: ").append(occurred).append("\n");

		return builder.toString();
	}

}
