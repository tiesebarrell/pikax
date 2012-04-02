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
package org.pikax.core.configuration;

import java.io.File;

/**
 * @author Tiese Barrell
 *
 */
public final class ConfigurationFactory {

	private ConfigurationFactory() {
		super();
	}
	
	public static final Configuration newInstance() {
		final Configuration result = new Configuration();
		
		result.setOutputFormat(OutputFormat.DEFAULT);
		result.setLogEventRetention(10);
		result.setOutputLocation(new File(""));
		
		return result;
	}

}
