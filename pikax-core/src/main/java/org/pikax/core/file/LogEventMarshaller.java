/**
 * 
 */
package org.pikax.core.file;

import org.pikax.core.LogEvent;

/**
 * @author Tiese Barrell
 * 
 */
public interface LogEventMarshaller {

	String marshallBegin();

	String marshallEvent(LogEvent logEvent);

}
