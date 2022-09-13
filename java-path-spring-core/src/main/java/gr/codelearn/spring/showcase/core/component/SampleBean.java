package gr.codelearn.spring.showcase.core.component;

import gr.codelearn.spring.showcase.core.base.BaseComponent;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SampleBean extends BaseComponent {
	private final AnotherSampleBean anotherSampleBean;

	@Value("${sample.value:'None'}")
	private String sampleValue;

	public void sayHello() {
		logger.info("Hello from Sample Bean calling {}.", anotherSampleBean.getClassName());
		logger.info("Retrieved property value '{}'.", sampleValue);
	}
}
