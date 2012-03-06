package org.pikax.log.generator;

import java.util.List;

public interface AuditCallback {

    void audit(AuditEntry auditEntry);

    void reset();

    void start();

    void stop();

    List<AuditEntry> getAuditEntries();

}
