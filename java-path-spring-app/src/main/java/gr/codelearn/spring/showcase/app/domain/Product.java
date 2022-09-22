package gr.codelearn.spring.showcase.app.domain;

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
