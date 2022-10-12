package gr.codelearn.spring.showcase.app.repository;

import gr.codelearn.spring.showcase.app.domain.Customer;
import gr.codelearn.spring.showcase.app.transfer.KeyValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
	Optional<Customer> findByEmail(final String email);

	@Query(name = "Customer.purchasedMostExpensiveProduct", nativeQuery = true)
	List<KeyValue<String, Long>> findCustomersPurchasedMostExpensiveProduct();
}
