package gr.codelearn.spring.showcase.app.service;

import gr.codelearn.spring.showcase.app.domain.Customer;
import gr.codelearn.spring.showcase.app.transfer.KeyValue;

import java.util.List;

public interface CustomerService extends BaseService<Customer, Long> {
	Customer findByEmail(String email);

	List<KeyValue<String, Long>> findCustomersPurchasedMostExpensiveProduct();
}
