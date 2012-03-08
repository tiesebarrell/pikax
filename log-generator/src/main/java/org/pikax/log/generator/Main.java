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
