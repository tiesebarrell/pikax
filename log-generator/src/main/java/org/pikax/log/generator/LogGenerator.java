/**
 * 
 */
package org.pikax.log.generator;

import java.io.File;

/**
 * @author tiesebarrell
 */
public interface LogGenerator {

    void generate(final File outputLocation, final int amount);

}
