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

		executorService = Executors.newFixedThreadPool(250);

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
