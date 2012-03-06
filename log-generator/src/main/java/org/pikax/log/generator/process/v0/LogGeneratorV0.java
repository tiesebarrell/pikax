/**
 * 
 */
package org.pikax.log.generator.process.v0;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.activiti.engine.RuntimeService;
import org.pikax.log.generator.PikaxLogGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * @author tiesebarrell
 */
@Component
public class LogGeneratorV0 extends PikaxLogGenerator {

    private static final String PROCESS_KEY = "discologgenerator0";

    @Autowired
    private transient RuntimeService runtimeService;

    /**
     * {@inheritDoc}
     * 
     * @see org.pikax.log.generator.PikaxLogGenerator#simulateProcess()
     */
    @Override
    protected void simulateProcess() {
        final Map<String, Object> variableMap = new HashMap<String, Object>();
        variableMap.put("id", UUID.randomUUID().toString());

        runtimeService.startProcessInstanceByKey(PROCESS_KEY, variableMap);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.pikax.log.generator.PikaxLogGenerator#getGeneratorName()
     */
    @Override
    protected String getGeneratorName() {
        return "variant0";
    }

}
