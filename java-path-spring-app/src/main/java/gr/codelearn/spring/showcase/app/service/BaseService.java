package gr.codelearn.spring.showcase.app.service;

import gr.codelearn.spring.showcase.app.domain.BaseModel;

import java.util.List;

public interface BaseService<T extends BaseModel, ID> {
	T create(final T item);

	List<T> createAll(final T... items);

	List<T> createAll(final List<T> items);

	void update(T item);

	void delete(T item);

	void deleteById(ID id);

	boolean exists(T item);

	T get(ID id);

	List<T> findAll();
}
