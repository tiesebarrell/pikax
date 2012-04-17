package org.pikax.core.file.csv.stub;

import java.util.Calendar;
import java.util.UUID;

public class AggregatedEvent {
	
	private final String id;
	private final String caseId;
	private final Calendar occurredStart;
	private final Calendar occurredEnd;
	private final String name;

	protected AggregatedEvent(final String caseId, final String name, final Calendar occurredStart, final Calendar occurredEnd) {
		super();
		this.id = UUID.randomUUID().toString();
		this.caseId = caseId;
		this.occurredStart = occurredStart;
		this.occurredEnd = occurredEnd;
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public String getCaseId() {
		return caseId;
	}
	
	public Calendar getOccurredStart() {
		return occurredStart;
	}
	
	public Calendar getOccurredEnd() {
		return occurredEnd;
	}

	public String getName() {
		return name;
	}

}
