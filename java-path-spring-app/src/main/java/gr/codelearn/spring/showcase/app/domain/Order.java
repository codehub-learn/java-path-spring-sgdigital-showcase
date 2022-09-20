package gr.codelearn.spring.showcase.app.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order extends BaseModel {
	@NotNull
	private Customer customer;

	private Date submitDate;

	@ToString.Exclude
	@NotNull
	private Set<@NotNull OrderItem> orderItems;

	@NotNull
	private PaymentMethod paymentMethod;

	private BigDecimal cost;
}
