package org.pikax.log.generator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class SimpleAuditCallback implements AuditCallback {

    private List<AuditEntry> auditEntries;

    private boolean isStarted = false;

    public SimpleAuditCallback() {
        super();
        reset();
    }

    public void audit(final AuditEntry auditEntry) {
        if (isStarted) {
            auditEntries.add(auditEntry);
        }
    }

    public void reset() {
        auditEntries = new ArrayList<AuditEntry>();
    }

    public void start() {
        isStarted = true;
    }

    public void stop() {
        isStarted = false;
        Collections.sort(auditEntries);
    }

    public List<AuditEntry> getAuditEntries() {
        return auditEntries;
    }

}
