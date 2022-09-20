package gr.codelearn.spring.showcase.app.repository;

import gr.codelearn.spring.showcase.app.domain.Customer;

import java.util.Optional;

public interface CustomerRepository extends BaseRepository<Customer, Long> {
	Optional<Customer> findByEmail(final String email);
}
