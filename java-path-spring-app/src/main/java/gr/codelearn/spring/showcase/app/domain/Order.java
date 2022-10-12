package gr.codelearn.spring.showcase.app.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
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
@Entity
@Table(name = "ORDERS")
@SequenceGenerator(name = "idGenerator", sequenceName = "ORDERS_SEQ", initialValue = 1, allocationSize = 1)
public class Order extends BaseModel {
	@NotNull
	@ManyToOne
	private Customer customer;

	@Column(nullable = false)
	private Date submitDate;

	@ToString.Exclude
	@NotNull
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<@NotNull OrderItem> orderItems;

	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(length = 15, nullable = false)
	private PaymentMethod paymentMethod;

	@Column(precision = 10, scale = 2, nullable = false)
	private BigDecimal cost;
}
