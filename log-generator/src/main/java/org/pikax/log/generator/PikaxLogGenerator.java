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

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author tiesebarrell
 */
public abstract class PikaxLogGenerator implements LogGenerator {

	private ExecutorService executorService;

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.pikax.log.generator.LogGenerator#generate(int)
	 */
	public void generate(final int amount) {

		executorService = Executors.newFixedThreadPool(1);

		for (int i = 0; i < amount; i++) {
			System.out.println("Creating process handler " + i);
			final ProcessHandler processHandler = new ProcessHandler();
			executorService.execute(processHandler);
		}

		executorService.shutdown();

		try {
			Thread.sleep(2500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	protected abstract void simulateProcess();

	protected abstract String getGeneratorName();

	private class ProcessHandler implements Runnable {
		public void run() {
			System.out.println("Simulating process");
			simulateProcess();
		}
	}

}
