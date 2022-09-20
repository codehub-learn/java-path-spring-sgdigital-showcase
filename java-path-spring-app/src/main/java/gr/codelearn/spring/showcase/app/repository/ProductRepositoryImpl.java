package gr.codelearn.spring.showcase.app.repository;

import gr.codelearn.spring.showcase.app.domain.Product;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class ProductRepositoryImpl extends BaseRepositoryImpl<Product> implements ProductRepository {
	private final AtomicLong sequence = new AtomicLong(1);
	private final Map<Long, Product> storage = new LinkedHashMap<>();

	@Override
	public Map<Long, Product> getStorage() {
		return storage;
	}

	@Override
	public AtomicLong getSequence() {
		return sequence;
	}

	@Override
	public Optional<Product> findBySerial(final String serial) {
		return storage.values().stream().filter(p -> p.getSerial().equalsIgnoreCase(serial)).findFirst();
	}
}
