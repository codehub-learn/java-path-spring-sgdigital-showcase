package gr.codelearn.spring.showcase.app.domain;

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
public class Product extends BaseModel {
	@NotNull
	private String serial;
	@NotNull
	private String name;
	@NotNull
	private BigDecimal price;
	@NotNull
	private Category category;
}
