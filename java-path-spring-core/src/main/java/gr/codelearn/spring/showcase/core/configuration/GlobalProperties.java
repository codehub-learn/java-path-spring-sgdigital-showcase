package gr.codelearn.spring.showcase.core.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.List;

@Configuration
@PropertySource("classpath:global-${spring.profiles.active}.properties")
@ConfigurationProperties(prefix = "app")
@Data
public class GlobalProperties {
	private int threadPool;
	private String email;
	private List<String> errorCodes;
}
