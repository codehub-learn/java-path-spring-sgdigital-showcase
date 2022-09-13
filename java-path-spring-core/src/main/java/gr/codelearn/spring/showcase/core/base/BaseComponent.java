package gr.codelearn.spring.showcase.core.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public abstract class BaseComponent {
	protected Logger logger = LoggerFactory.getLogger(getClass());

	@PostConstruct
	public void init() {
		logger.trace("{} is initialized.", getClass().getName());
	}

	@PreDestroy
	public void shutdown() {
		logger.trace("{} is about to be destroyed.", getClass().getName());
	}
}
