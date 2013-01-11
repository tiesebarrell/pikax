/**
 * 
 */
package org.pikax.core;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.pikax.core.configuration.Configuration;
import org.pikax.core.configuration.ConfigurationFactory;
import org.pikax.core.configuration.OutputFormat;

/**
 * @author Tiese Barrell
 *
 */
public class PikaxProcessorFactoryTest {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testDefaultConfiguration() {
		
		final Configuration configuration = ConfigurationFactory.newInstance();
		
		assertEquals(OutputFormat.DEFAULT, configuration.getOutputFormat());
		assertEquals(10, configuration.getLogEventRetention());
		//assertEquals(OutputFormat.DEFAULT, configuration.getOutputFormat());
		
	}

}
