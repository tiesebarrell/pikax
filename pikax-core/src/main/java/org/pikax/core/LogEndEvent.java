/**
 * 
 */
package org.pikax.core;

import java.util.Calendar;

/**
 * @author tiesebarrell
 * 
 */
public class LogEndEvent extends AbstractSingleDateLogEvent {

	public LogEndEvent(String caseId, String auditActivity, Calendar end) {
		super(caseId, auditActivity, end);
	}

}
