package gr.codelearn.spring.showcase.app.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
