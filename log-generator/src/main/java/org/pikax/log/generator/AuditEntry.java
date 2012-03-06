/**
 * 
 */
package org.pikax.log.generator;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.UUID;

/**
 * @author tiesebarrell
 */
public class AuditEntry implements Comparable<AuditEntry> {

    public static final String CSV_HEADER_LINE = "ID,CaseID,Activity,Start,End\n";

    private static final String TIMESTAMP_DATE_PATTERN = "yyyy-MM-dd HH:mm:ss.S";

    private static final String CSV_PATTERN = "%s,%s,%s,%s,%s\n";

    private final String id;

    private final String caseId;
    private final Calendar start;
    private final Calendar end;
    private final String auditActivity;

    /**
     * @param caseId
     * @param start
     * @param end
     * @param auditActivity
     */
    public AuditEntry(final String caseId, final Calendar start, final Calendar end, final String auditActivity) {
        super();
        this.id = UUID.randomUUID().toString();
        this.caseId = caseId;
        this.start = start;
        this.end = end;
        this.auditActivity = auditActivity;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @return the caseId
     */
    public String getCaseId() {
        return caseId;
    }

    /**
     * @return the start
     */
    public String getStart() {
        return new SimpleDateFormat(TIMESTAMP_DATE_PATTERN).format(start.getTime());
    }

    /**
     * @return the end
     */
    public String getEnd() {
        return new SimpleDateFormat(TIMESTAMP_DATE_PATTERN).format(end.getTime());
    }

    /**
     * @return the auditActivity
     */
    public String getAuditActivity() {
        return auditActivity;
    }

    public int compareTo(final AuditEntry o) {
        int result = 0;
        if (o != null) {
            result = start.compareTo(o.start);
        }
        return result;
    }

    public String createCSV() {
        return String.format(CSV_PATTERN, getId(), getCaseId(), getAuditActivity(), getStart(), getEnd());
    }

    /**
     * {@inheritDoc}
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Case: ").append(caseId).append("\n").append("Activity name: ").append(auditActivity)
                .append("\n").append("Start: ").append(start).append("\n").append("End: ").append(end);

        return builder.toString();
    }

}
