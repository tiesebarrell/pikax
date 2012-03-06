/**
 * 
 */
package org.pikax.log.generator;

import org.apache.camel.builder.RouteBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

/**
 * RouteBuilder.
 * 
 * @author Tiese Barrell
 * @since 1.0.0
 * @version 1
 */
@Component
public class EventingRouteBuilder extends RouteBuilder {

    private static final Log LOG = LogFactory.getLog(EventingRouteBuilder.class);

    /*
     * (non-Javadoc)
     * @see org.apache.camel.builder.RouteBuilder#configure()
     */
    @Override
    public void configure() throws Exception {
        LOG.info(String.format("Starting route %s", getClass().getCanonicalName()));

        from("seda:events").log("Received event").bean(EventService.class, "handle").log("Handled event");

        LOG.info(String.format("Started route %s", getClass().getCanonicalName()));
    }

}
