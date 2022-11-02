package gr.codelearn.spring.showcase.app.repository;

import gr.codelearn.spring.showcase.app.domain.Order;
import gr.codelearn.spring.showcase.app.transfer.KeyValue;
import gr.codelearn.spring.showcase.app.transfer.PurchasesCostPerCustomerCategoryDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.stereotype.Repository;

import javax.persistence.QueryHint;
import java.math.BigDecimal;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
	@Query("""
			           select new gr.codelearn.spring.showcase.app.transfer.KeyValue(concat(c.firstname, ' ', c.lastname),
					avg(o.cost))
					from Order o
					join o.customer c group by c.firstname
			""")
	List<KeyValue<String, BigDecimal>> findAverageOrderCostPerCustomer();

	@Query("select distinct o from Order o join fetch o.customer join fetch o.orderItems oi join fetch oi.product where o.id = :id")
	@QueryHints(@QueryHint(name = org.hibernate.jpa.QueryHints.HINT_PASS_DISTINCT_THROUGH, value = "false"))
	Order getLazy(Long id);

	@Query(nativeQuery = true)
	List<PurchasesCostPerCustomerCategoryDto> findTotalNumberOfOrdersAndCostOfPurchasesPerCustomerCategory();
}
