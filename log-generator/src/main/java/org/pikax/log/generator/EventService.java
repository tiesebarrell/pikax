/**
 * 
 */
package org.pikax.log.generator;

import java.util.Calendar;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.ProducerTemplate;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service for handling {@link Event}s.
 * 
 * @author Tiese Barrell
 * @since 1.0.0
 * @version 1
 */
@Service
public class EventService {

    private static final Log LOG = LogFactory.getLog(EventService.class);

    @Autowired
    private AuditCallback auditCallback;

    @Autowired
    private transient CamelContext camelContext;

    public void sendAuditEvent(final String caseId, final String activityName, final Calendar start, final Calendar end) {
        final ProducerTemplate template = camelContext.createProducerTemplate();
        template.sendBody("seda:events", new AuditEntry(caseId, start, end, activityName));
    }

    public void handle(final Exchange exchange) {

        final Object body = exchange.getIn().getBody();

        if (body instanceof AuditEntry) {
            LOG.info("Handling event: " + body);
            auditCallback.audit((AuditEntry) body);
        } else {
            LOG.info("No event handlers found for event: " + body);
        }

    }

}
