package gr.codelearn.spring.showcase.app.repository;

import gr.codelearn.spring.showcase.app.domain.Category;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class CategoryRepositoryImpl extends BaseRepositoryImpl<Category> implements CategoryRepository {
	private final AtomicLong sequence = new AtomicLong(1);
	private final Map<Long, Category> storage = new LinkedHashMap<>();

	@Override
	public Map<Long, Category> getStorage() {
		return storage;
	}

	@Override
	public AtomicLong getSequence() {
		return sequence;
	}

	@Override
	public Optional<Category> findByDescription(final String description) {
		return storage.values().stream().filter(c -> c.getDescription().equalsIgnoreCase(description)).findFirst();
	}
}
