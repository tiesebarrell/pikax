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
package org.pikax.core.file.csv;

import org.pikax.core.LogEvent;
import org.pikax.core.file.LogEventMarshaller;

public class CsvLogEventMarshaller implements LogEventMarshaller {

	private static final String CSV_HEADER_LINE = "ID,CaseID,Activity,Start,End\n";

	private static final String CSV_PATTERN = "%s,%s,%s,%s,%s\n";

	public String marshallEvent(LogEvent logEvent) {
		return String.format(CSV_PATTERN, logEvent.getId(), logEvent.getCaseId(), logEvent.getAuditActivity(),
				logEvent.getStart(), logEvent.getEnd());
	}

	public String marshallBegin() {
		return CSV_HEADER_LINE;
	}

}
