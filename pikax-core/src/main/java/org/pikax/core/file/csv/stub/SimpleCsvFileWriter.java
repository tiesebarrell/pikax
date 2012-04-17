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
package org.pikax.core.file.csv.stub;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.pikax.core.event.Event;

public final class SimpleCsvFileWriter {

	private static final String FILE_NAME_PATTERN = "pikax.log.%s.csv";
	private static final LogEventMarshaller logEventMarshaller = new CsvLogEventMarshaller();

	private static final LogEventCache LOG_EVENT_CACHE = new FileLogEventCache();

	private static boolean closed = true;

	private static File LOGFILE;

	private SimpleCsvFileWriter() {
		super();
	}

	static {
		rollLogFile();
	}

	public synchronized static final void rollLogFile() {
		final String dateString = new SimpleDateFormat("yyyy-MM-dd.HHmmssSZ").format(new Date());
		LOGFILE = new File(String.format(FILE_NAME_PATTERN, dateString));
	}

	public synchronized static final void writeEvent(final Event event) {
		final AggregatedEvent toBeWritten = LOG_EVENT_CACHE.cache(event);
		if (toBeWritten != null) {
			writeEventToFile(toBeWritten);
		}
	}

	private static final void writeEventToFile(final AggregatedEvent event) {
		try {
			prepareWrite();
			writeToFile(logEventMarshaller.marshallEvent(event));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private synchronized static void prepareWrite() throws IOException {
		if (closed) {
			prepareFile();
			writeToFile(logEventMarshaller.marshallBegin());
			closed = false;
		}
	}

	private synchronized static void writeToFile(final String line) throws IOException {
		FileUtils.write(LOGFILE, line, true);
	}

	private synchronized static void prepareFile() throws IOException {
		if (!LOGFILE.exists()) {
			LOGFILE.createNewFile();
		}
	}
}
