package gr.codelearn.spring.showcase.app.config;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashSet;
import java.util.Set;

@Configuration
public class ProductFilterConfiguration {
	private static final Set<String> ignorableFieldNames = new HashSet<>();

	static {
		// Default exclusion field list
		ignorableFieldNames.add("name");
	}

	@Bean
	public Jackson2ObjectMapperBuilderCustomizer jsonFilterCustomizer() {
		SimpleFilterProvider simpleFilterProvider = new SimpleFilterProvider().setFailOnUnknownId(false);
		FilterProvider filters = simpleFilterProvider.addFilter("product_filter",
																SimpleBeanPropertyFilter.serializeAllExcept(
																		ignorableFieldNames));
		// We can also add automatic filters such as not showing nulls etc.
		//builder.serializationInclusion(JsonInclude.Include.NON_NULL)
		return builder -> builder.filters(filters);
	}
}
