/**
 * 
 */
package org.pikax.core;

import java.util.Calendar;

/**
 * @author tiesebarrell
 * 
 */
public class LogStartEvent extends AbstractSingleDateLogEvent {

	public LogStartEvent(String caseId, String auditActivity, Calendar start) {
		super(caseId, auditActivity, start);
	}

}
