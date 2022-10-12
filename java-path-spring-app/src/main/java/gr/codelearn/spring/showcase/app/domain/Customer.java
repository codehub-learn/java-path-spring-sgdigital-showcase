package gr.codelearn.spring.showcase.app.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
