package gr.codelearn.spring.showcase.app.repository;

import gr.codelearn.spring.showcase.app.base.BaseComponent;
import gr.codelearn.spring.showcase.app.domain.BaseModel;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;

public abstract class BaseRepositoryImpl<T extends BaseModel> extends BaseComponent implements BaseRepository<T, Long> {
	public abstract Map<Long, T> getStorage();

	public abstract AtomicLong getSequence();

	@Override
	public T create(final T entity) {
		final Long key = getSequence().getAndIncrement();
		entity.setId(key);
		getStorage().put(key, entity);
		logger.trace("Created {}.", entity);
		return entity;
	}

	@Override
	public void update(final T entity) {
		getStorage().put(entity.getId(), entity);
		logger.trace("Updated {}.", entity);
	}

	@Override
	public void delete(final T entity) {
		deleteById(entity.getId());
	}

	@Override
	public void deleteById(final Long id) {
		logger.trace("{} entity with id:{}.", getStorage().remove(id) != null ? "Deleted" : "Did not delete", id);
	}

	@Override
	public boolean exists(final T entity) {
		boolean found = Objects.nonNull(getStorage().get(entity.getId()));
		logger.trace("Found {}.", entity);
		return found;
	}

	@Override
	public T get(final Long id) {
		return getStorage().get(id);
	}

	@Override
	public List<T> findAll() {
		return getStorage().values().stream().toList();
	}
}
