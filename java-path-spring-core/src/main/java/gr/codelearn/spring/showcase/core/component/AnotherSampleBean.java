package gr.codelearn.spring.showcase.core.component;

import gr.codelearn.spring.showcase.core.base.BaseComponent;
import org.springframework.stereotype.Component;

@Component
public class AnotherSampleBean extends BaseComponent {
	public void sayHello() {
		logger.info("Hello from Another Sample Bean");
	}

	public String getClassName() {
		return this.getClass().getName();
	}
}
