package gr.codelearn.spring.showcase.app.repository;

import gr.codelearn.spring.showcase.app.domain.BaseModel;

import java.util.List;

public interface BaseRepository<T extends BaseModel, ID> {
	T create(final T entity);

	void update(T entity);

	void delete(T entity);

	void deleteById(ID id);

	boolean exists(T entity);

	T get(ID id);

	List<T> findAll();
}
