package gr.codelearn.spring.showcase.core.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {
	private Long id;
	private String email;
	private String firstname;
	private String lastname;
	private String address;
}
