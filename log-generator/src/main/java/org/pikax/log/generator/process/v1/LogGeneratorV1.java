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
package org.pikax.log.generator.process.v1;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.activiti.engine.RuntimeService;
import org.pikax.log.generator.PikaxLogGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author tiesebarrell
 */
@Component
public class LogGeneratorV1 extends PikaxLogGenerator {

	private static final String PROCESS_KEY = "loggenerator1";

	@Autowired
	private transient RuntimeService runtimeService;

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.pikax.log.generator.PikaxLogGenerator#simulateProcess()
	 */
	@Override
	protected void simulateProcess() {
		final Map<String, Object> variableMap = new HashMap<String, Object>();
		variableMap.put("id", UUID.randomUUID().toString());

		runtimeService.startProcessInstanceByKey(PROCESS_KEY, variableMap);

	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.pikax.log.generator.PikaxLogGenerator#getGeneratorName()
	 */
	@Override
	protected String getGeneratorName() {
		return "variant1";
	}

}
