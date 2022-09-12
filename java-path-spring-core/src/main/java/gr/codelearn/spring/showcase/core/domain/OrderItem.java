package gr.codelearn.spring.showcase.core.domain;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class OrderItem {
	private Long id;
	private String serial; // Should this be changed to product?
	private Integer quantity;
	private BigDecimal price;
}
