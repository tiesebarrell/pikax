package org.pikax.log.generator;

import org.pikax.log.generator.process.v0.LogGeneratorV0;
import org.pikax.log.generator.process.v1.LogGeneratorV1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public final class LogGeneratorFactory {

    @Autowired
    private transient LogGeneratorV0 discoLogGeneratorV0;

    @Autowired
    private transient LogGeneratorV1 discoLogGeneratorV1;

    public LogGenerator newInstance(final int variant) {
        switch (variant) {
        case 1:
            return discoLogGeneratorV1;
        default:
            return discoLogGeneratorV0;
        }
    }

}
