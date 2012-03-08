package org.pikax.log.generator;

import org.pikax.log.generator.process.v1.LogGeneratorV1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public final class LogGeneratorFactory {

	@Autowired
	private transient LogGeneratorV1 logGeneratorV1;

	public LogGenerator newInstance(final int variant) {
		switch (variant) {
		default:
			return logGeneratorV1;
		}
	}

}
