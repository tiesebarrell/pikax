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
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.pikax.core.event.Event;

public class FileLogEventCache implements LogEventCache {

	private static final String FILE_NAME_PATTERN = "pikax.correlation.cache";
	private static final File CACHEFILE = new File(FILE_NAME_PATTERN);

	private Map<String, Event> cache = new HashMap<String, Event>();

	public FileLogEventCache() {
		super();
		loadCacheFromFile();
	}

	@SuppressWarnings("unchecked")
	private void loadCacheFromFile() {
		if (CACHEFILE.exists()) {
			try {
				final ObjectInputStream ois = new ObjectInputStream(new FileInputStream(CACHEFILE));
				cache = (Map<String, Event>) ois.readObject();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}

	public synchronized AggregatedEvent cache(Event event) {

		AggregatedEvent result = null;
		if (isCorrelatedEventInCache(event)) {
			result = createLogEvent(event);
		} else {
			addToCache(event);
		}

		String message = String.format("Processed event. The cache now contains %s events awaiting correlation.",
				cache.size());
		if (cache.size() > 0 && cache.size() < 6) {
			final StringBuilder builder = new StringBuilder();
			for (final Entry<String, Event> entry : cache.entrySet()) {
				builder.append("\n\t CorrelationID: ").append(entry.getKey());
			}
			message = message + builder.toString();
		}
		System.out.println(message);
		return result;
	}

	private void addToCache(Event event) {
		cache.put(event.getCorrelationId(), event);
		persistCache();
	}

	private AggregatedEvent createLogEvent(Event event) {
		AggregatedEvent result = null;
		final Event correlatedEvent = cache.get(event.getCorrelationId());
		if (correlatedEvent != null) {
			result = new AggregatedEvent(correlatedEvent.getCaseId(), correlatedEvent.getName(), correlatedEvent.getOccurred(), event.getOccurred());
			cache.remove(event.getCorrelationId());
			persistCache();
		}
		return result;
	}

	private boolean isCorrelatedEventInCache(Event event) {
		return cache.containsKey(event.getCorrelationId());
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
