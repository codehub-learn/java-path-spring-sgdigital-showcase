package gr.codelearn.spring.showcase.app.repository;

import gr.codelearn.spring.showcase.app.domain.Product;

import java.util.Optional;

public interface ProductRepository extends BaseRepository<Product, Long> {
	Optional<Product> findBySerial(final String serial);
}
