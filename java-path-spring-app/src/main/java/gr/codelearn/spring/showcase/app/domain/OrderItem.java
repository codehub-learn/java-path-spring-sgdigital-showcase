package gr.codelearn.spring.showcase.app.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
@ToString(callSuper = true)
@Builder
public class OrderItem extends BaseModel {
	@NotNull
	private Product product;

	@NotNull
	@JsonIgnore
	private Order order;

	@NotNull
	private Integer quantity;

	@NotNull
	private BigDecimal price;
}
