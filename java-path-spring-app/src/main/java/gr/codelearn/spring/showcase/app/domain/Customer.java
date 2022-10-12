package gr.codelearn.spring.showcase.app.domain;

import gr.codelearn.spring.showcase.app.transfer.KeyValue;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

//@formatter:off
@NamedNativeQuery(name = "Customer.purchasedMostExpensiveProduct",
		query =
				"SELECT C.FIRSTNAME || ' ' || C.LASTNAME as fullname, COUNT(*) as purchases " +
						"FROM ORDERS O, ORDER_ITEMS OI, CUSTOMERS C " +
						"WHERE OI.ORDER_ID = O.ID " +
						"AND O.CUSTOMER_ID = C.ID " +
						"AND OI.PRODUCT_ID = (SELECT TOP 1 ID FROM PRODUCTS ORDER BY PRICE DESC) " +
						"GROUP BY O.CUSTOMER_ID " +
						"ORDER BY purchases, c.lastname, c.firstname",
		resultSetMapping = "CustomersPurchasedMostExpensiveProduct")
@SqlResultSetMapping(name = "CustomersPurchasedMostExpensiveProduct",
		classes = @ConstructorResult(
				targetClass = KeyValue.class,
				columns = {
						@ColumnResult(name = "fullname", type = String.class),
						@ColumnResult(name = "purchases", type = Long.class)
				}
		)
)
//@formatter:on
@Getter
@Setter
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "CUSTOMERS")
@SequenceGenerator(name = "idGenerator", sequenceName = "CUSTOMERS_SEQ", initialValue = 1, allocationSize = 1)
public class Customer extends BaseModel {
	@NotNull
	@Email
	@Column(length = 50, nullable = false, unique = true)
	private String email;
	@NotNull
	@Column(length = 20, nullable = false)
	private String firstname;
	@NotNull
	@Column(length = 20, nullable = false)
	private String lastname;
	@Min(value = 13, message = "too young for this application")
	@Max(value = 140, message = "too old for this application")
	private Integer age;
	@Size(max = 50)
	@Column(length = 50)
	private String address;
	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(length = 10, nullable = false)
	private CustomerCategory customerCategory;
}
