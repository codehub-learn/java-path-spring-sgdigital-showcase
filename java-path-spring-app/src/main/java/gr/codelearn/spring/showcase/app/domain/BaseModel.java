package gr.codelearn.spring.showcase.app.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@ToString
public abstract class BaseModel implements Serializable {
	@Serial
	private static final long serialVersionUID = 1L;
	private Long id;
}
