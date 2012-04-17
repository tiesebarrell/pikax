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
import org.pikax.core.configuration.ConfigurationFactory;
import org.pikax.core.event.Event;
import org.pikax.core.file.csv.stub.SimpleCsvFileWriter;
import org.springframework.stereotype.Component;

/**
 * @author Tiese Barrell
 *
 */
@Component
public class PikaxProcessor {
	
	private transient Configuration configuration;
	
	public PikaxProcessor() {
		super();
		this.configuration = ConfigurationFactory.newInstance();
	}
	
	public PikaxProcessor(final Configuration configuration) {
		super();
		this.configuration = configuration;
	}
	
	public void processEvent(final Event event) {
		//TODO: use configuration instead of stub marshaller
		SimpleCsvFileWriter.writeEvent(event);
	}

}
