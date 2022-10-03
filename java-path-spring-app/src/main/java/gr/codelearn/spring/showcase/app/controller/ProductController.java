package gr.codelearn.spring.showcase.app.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import gr.codelearn.spring.showcase.app.base.BaseMapper;
import gr.codelearn.spring.showcase.app.domain.Product;
import gr.codelearn.spring.showcase.app.mapper.ProductMapper;
import gr.codelearn.spring.showcase.app.service.BaseService;
import gr.codelearn.spring.showcase.app.service.ProductService;
import gr.codelearn.spring.showcase.app.transfer.ApiResponse;
import gr.codelearn.spring.showcase.app.transfer.resource.ProductResource;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController extends AbstractController<Product, ProductResource> {
	private final ProductService productService;
	private final ProductMapper productMapper;

	@Override
	public BaseService<Product, Long> getBaseService() {
		return productService;
	}

	@Override
	public BaseMapper<Product, ProductResource> getMapper() {
		return productMapper;
	}

	@GetMapping(params = "serial")
	public ResponseEntity<ApiResponse<ProductResource>> findBySerial(@RequestParam String serial) {
		final ProductResource productResource = getMapper().toResource(productService.findBySerial(serial));
		return ResponseEntity.ok(ApiResponse.<ProductResource>builder().data(productResource).build());
	}

	@GetMapping(params = "serial", headers = {"filter", "api-v"})
	public ResponseEntity<ApiResponse<JsonNode>> findBySerialFiltered(@RequestParam String serial,
																	  @RequestHeader(value = "filter", defaultValue = "price") String filter,
																	  @RequestHeader(value = "api-v", defaultValue = "1") Integer apiVersion) {
		final ProductResource productResource = getMapper().toResource(productService.findBySerial(serial));

		// If you define API version 2, you get to select the fields excluded
		if (apiVersion == 2) {
			//@formatter:off

			// Get rid of spaces
			Set<String> filterFields = Arrays.stream(filter.split(","))
											 .map(String::trim)
											 .collect(Collectors.toSet());
			return ResponseEntity.ok(ApiResponse.<JsonNode>builder()
												.data(filterProductAndConvertToString(productResource, filterFields))
												.build());
			//@formatter:on
		}

		// If you define API version 1 or nothing, you will get the fixed attribute excluded
		return ResponseEntity.ok(
				ApiResponse.<JsonNode>builder().data(filterProductAndConvertToString(productResource, Set.of("price")))
						   .build());
	}

	private JsonNode filterProductAndConvertToString(ProductResource productResource, Set<String> excludedAttributes) {
		/*
		 * Method that filters a given product and returns it based on what attributes we have chosen to exclude
		 * This may be considered a way of dynamic filtering without setting a "global filter" in a product
		 * configuration
		 */
		SimpleFilterProvider simpleFilterProvider = new SimpleFilterProvider().setFailOnUnknownId(false);
		FilterProvider filters = simpleFilterProvider.addFilter("product_filter",
																SimpleBeanPropertyFilter.serializeAllExcept(
																		excludedAttributes));

		// Local object mapper that will convert our product to a string based on the given filter
		ObjectMapper mapper = new ObjectMapper();
		mapper.setFilterProvider(filters);
		// We can also add automatic filters such as not showing nulls etc.
		//mapper.serializationInclusion(JsonInclude.Include.NON_NULL)
		ObjectWriter writer = mapper.writer();
		final String filteredJsonAsString;
		try {
			// Converting to string to filter out attributes we have excluded
			filteredJsonAsString = writer.writeValueAsString(productResource);
			return mapper.readTree(filteredJsonAsString);
		} catch (JsonProcessingException e) {
			// Will be caught by the exception handler which handles generic exceptions
			throw new RuntimeException("Json processing has failed");
		}
	}
}
