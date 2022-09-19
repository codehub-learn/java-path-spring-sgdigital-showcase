package gr.codelearn.spring.showcase.core.bootstrap;

import gr.codelearn.spring.showcase.core.base.BaseComponent;
import gr.codelearn.spring.showcase.core.configuration.GlobalProperties;
import gr.codelearn.spring.showcase.core.configuration.GlobalYamlProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@Order(0)
@RequiredArgsConstructor
public class PropertiesRunner extends BaseComponent implements CommandLineRunner {
	private final GlobalProperties globalProperties;
	private final GlobalYamlProperties globalYamlProperties;

	@Value("${app.email:default@email.com}")
	private String email;

	private final Environment environment;

	@Override
	public void run(final String... args) throws Exception {
		logger.info("Using properties, current thread pool is {}.", globalProperties.getThreadPool());
		logger.info("Using properties, current email is {}.", globalProperties.getEmail());
		logger.info("Using properties, current error code values are {}.", globalProperties.getErrorCodes());

		logger.info("Using yaml, current thread pool is {}.", globalYamlProperties.getThreadPool());
		logger.info("Using yaml, current email is {}.", globalYamlProperties.getEmail());
		logger.info("Using yaml, current server values are {}.", globalYamlProperties.getServers());
	}
}
