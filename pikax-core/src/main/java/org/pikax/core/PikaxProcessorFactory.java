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

import org.pikax.core.configuration.Configuration;

/**
 * Factory class for instances of {@link PikaxProcessor}. In addition to
 * creating instances, this class maintains a reference to a single instance
 * (referred to as "the instance"), which serves as a utility for the common
 * case where there is a singleton {@link PikaxProcessor} in an application. The
 * instance can be retrieved from the factory.
 * 
 * @author Tiese Barrell
 * 
 */
public final class PikaxProcessorFactory {

	private static PikaxProcessor instance;

	private PikaxProcessorFactory() {
		super();
	}

	/**
	 * Creates a new PikaxProcessor with default configuration. The new instance
	 * also becomes the current instance. To get the current instance, call
	 * {@link #getInstance()} instead.
	 * 
	 * @return a new PikaxProcessor
	 */
	public static final PikaxProcessor newInstance() {
		synchronized (instance) {
			instance = new PikaxProcessor();
		}
		return instance;
	}

	/**
	 * Creates a new PikaxProcessor with the provided configuration. The new
	 * instance also becomes the current instance. To get the current instance,
	 * call {@link #getInstance()} instead.
	 * 
	 * @return a new PikaxProcessor
	 */
	public static final PikaxProcessor newInstance(final Configuration configuration) {
		synchronized (instance) {
			instance = new PikaxProcessor(configuration);
		}
		return instance;
	}

	/**
	 * Gets the current instance. Creates a new instance with default
	 * configuration if there is currently no instance.
	 * 
	 * @return the instance.
	 */
	public static final PikaxProcessor getInstance() {
		if (instance == null) {
			return newInstance();
		}
		return instance;
	}

}
