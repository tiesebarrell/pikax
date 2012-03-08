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
package org.pikax.log.generator;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author tiesebarrell
 */
public class Main {

	private static ApplicationContext applicationContext;

	/**
	 * @param args
	 */
	public static void main(final String[] args) {
		int variant = getVariant(args);

		applicationContext = new ClassPathXmlApplicationContext("spring/application-context.xml");

		final LogGeneratorFactory logGeneratorFactory = getBean(LogGeneratorFactory.class);

		final LogGenerator logGenerator = logGeneratorFactory.newInstance(variant);

		logGenerator.generate(50000);

	}

	private static int getVariant(final String[] args) {
		int variant = 0;
		if (args != null && args.length > 0) {
			variant = Integer.parseInt(args[0]);
		}
		return variant;
	}

	private static <T> T getBean(final Class<T> clazz) {
		return applicationContext.getBean(clazz);
	}
}
