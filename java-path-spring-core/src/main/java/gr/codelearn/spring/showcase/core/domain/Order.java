package gr.codelearn.spring.showcase.core.domain;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@Builder
public class Order {
	private Long id;
	private User user;
	private Date submitDate;
	private List<OrderItem> orderItems;
	private BigDecimal cost;
}
