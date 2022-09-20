package gr.codelearn.spring.showcase.app.repository;

import gr.codelearn.spring.showcase.app.domain.Customer;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class CustomerRepositoryImpl extends BaseRepositoryImpl<Customer> implements CustomerRepository {
	private final AtomicLong sequence = new AtomicLong(1);
	private final Map<Long, Customer> storage = new LinkedHashMap<>();

	@Override
	public Map<Long, Customer> getStorage() {
		return storage;
	}

	@Override
	public AtomicLong getSequence() {
		return sequence;
	}

	@Override
	public Optional<Customer> findByEmail(final String email) {
		return storage.values().stream().filter(c -> c.getEmail().equalsIgnoreCase(email)).findFirst();
	}
}
