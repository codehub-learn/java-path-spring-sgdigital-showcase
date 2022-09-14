package gr.codelearn.spring.showcase.core.component;

import gr.codelearn.spring.showcase.core.base.BaseComponent;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Conditional(TestCondition.class)
@ConditionalOnBean(AnotherSampleBean.class)
public class TestConditionBean extends BaseComponent {
	@PostConstruct
	public void createStorageDirectories() {
		logger.debug("Simulating a conditional bean instantiation.");
	}
}
