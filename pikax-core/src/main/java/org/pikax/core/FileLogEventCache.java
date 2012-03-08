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
package org.pikax.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class FileLogEventCache implements LogEventCache {

	private static final String FILE_NAME_PATTERN = "pikax.correlation.cache";
	private static final File CACHEFILE = new File(FILE_NAME_PATTERN);

	private Map<String, AbstractSingleDateLogEvent> cache = new HashMap<String, AbstractSingleDateLogEvent>();

	public FileLogEventCache() {
		super();
		loadCacheFromFile();
	}

	@SuppressWarnings("unchecked")
	private void loadCacheFromFile() {
		if (CACHEFILE.exists()) {
			try {
				final ObjectInputStream ois = new ObjectInputStream(new FileInputStream(CACHEFILE));
				cache = (Map<String, AbstractSingleDateLogEvent>) ois.readObject();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}

	public synchronized LogEvent cache(AbstractSingleDateLogEvent logEvent) {

		LogEvent result = null;
		if (isCorrelatedEventInCache(logEvent)) {
			result = createLogEvent(logEvent);
		} else {
			addToCache(logEvent);
		}

		String message = String.format("Processed event. The cache now contains %s events awaiting correlation.",
				cache.size());
		if (cache.size() > 0 && cache.size() < 6) {
			final StringBuilder builder = new StringBuilder();
			for (final Entry<String, AbstractSingleDateLogEvent> entry : cache.entrySet()) {
				builder.append("\n\t CorrelationID: ").append(entry.getKey());
			}
			message = message + builder.toString();
		}
		System.out.println(message);
		return result;
	}

	private void addToCache(AbstractSingleDateLogEvent logEvent) {
		cache.put(logEvent.getCorrelationId(), logEvent);
		persistCache();
	}

	private LogEvent createLogEvent(AbstractSingleDateLogEvent logEvent) {
		LogEvent result = null;
		final AbstractSingleDateLogEvent correlatedEvent = cache.get(logEvent.getCorrelationId());
		if (correlatedEvent != null) {
			result = new LogEvent(correlatedEvent.getCaseId(), correlatedEvent.getAuditActivity(), correlatedEvent,
					logEvent);
			cache.remove(logEvent.getCorrelationId());
			persistCache();
		}
		return result;
	}

	private boolean isCorrelatedEventInCache(AbstractSingleDateLogEvent logEvent) {
		return cache.containsKey(logEvent.getCorrelationId());
	}

	private void persistCache() {
		try {
			prepareFile();
			writeCacheToFile();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void writeCacheToFile() throws IOException {
		final ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(CACHEFILE));
		oos.writeObject(cache);
		oos.close();
	}

	private void prepareFile() throws IOException {
		synchronized (this) {
			if (!CACHEFILE.exists()) {
				CACHEFILE.createNewFile();
			}
		}
	}

}
