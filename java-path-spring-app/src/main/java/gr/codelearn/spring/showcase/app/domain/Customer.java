package gr.codelearn.spring.showcase.app.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@ToString(callSuper = true)
@Builder
public class Customer extends BaseModel {
	@NotNull
	@Email
	private String email;
	@NotNull
	private String firstname;
	@NotNull
	private String lastname;
	@Min(value = 13, message = "too young for this application")
	@Max(value = 140, message = "too old for this application")
	private Integer age;
	@Size(max = 50)
	private String address;
	@NotNull
	private CustomerCategory customerCategory;
}
