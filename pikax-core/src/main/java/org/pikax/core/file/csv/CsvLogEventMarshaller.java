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
