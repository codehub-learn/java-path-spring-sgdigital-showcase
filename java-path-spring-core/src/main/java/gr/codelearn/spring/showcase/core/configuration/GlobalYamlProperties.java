package gr.codelearn.spring.showcase.core.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "myproperties")
@Data
public class GlobalYamlProperties {
	private int threadPool;
	private String email;
	private List<String> servers;
}
