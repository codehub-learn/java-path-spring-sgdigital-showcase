package gr.codelearn.spring.showcase.app.repository;

import gr.codelearn.spring.showcase.app.domain.Category;

import java.util.Optional;

public interface CategoryRepository extends BaseRepository<Category, Long> {
	Optional<Category> findByDescription(final String description);
}
