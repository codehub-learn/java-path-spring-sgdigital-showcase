package gr.codelearn.spring.showcase.app.repository;

import gr.codelearn.spring.showcase.app.domain.Order;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class OrderRepositoryImpl extends BaseRepositoryImpl<Order> implements OrderRepository {
	private final AtomicLong sequence = new AtomicLong(1);
	private final Map<Long, Order> storage = new LinkedHashMap<>();

	@Override
	public Map<Long, Order> getStorage() {
		return storage;
	}

	@Override
	public AtomicLong getSequence() {
		return sequence;
	}
}
