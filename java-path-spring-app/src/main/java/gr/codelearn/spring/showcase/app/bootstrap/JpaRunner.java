package gr.codelearn.spring.showcase.app.bootstrap;

import gr.codelearn.spring.showcase.app.base.BaseComponent;
import gr.codelearn.spring.showcase.app.domain.Category;
import gr.codelearn.spring.showcase.app.domain.Customer;
import gr.codelearn.spring.showcase.app.domain.CustomerCategory;
import gr.codelearn.spring.showcase.app.domain.Order;
import gr.codelearn.spring.showcase.app.domain.PaymentMethod;
import gr.codelearn.spring.showcase.app.domain.Product;
import gr.codelearn.spring.showcase.app.service.CategoryService;
import gr.codelearn.spring.showcase.app.service.CustomerService;
import gr.codelearn.spring.showcase.app.service.OrderService;
import gr.codelearn.spring.showcase.app.service.ProductService;
import gr.codelearn.spring.showcase.app.transfer.KeyValue;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
@Profile("runner")
@RequiredArgsConstructor
public class JpaRunner extends BaseComponent implements CommandLineRunner {
	private final CustomerService customerService;
	private final OrderService orderService;
	private final ProductService productService;
	private final CategoryService categoryService;

	@Override
	public void run(final String... args) throws Exception {
		final Category vegetables = Category.builder().description("Vegetables").build();
		categoryService.create(vegetables);
		logger.info("{}", vegetables);

		//@formatter:off
		List<Product> products = List.of(
				Product.builder().serial("SN1000-0001").name("Apple iPhone 12 Pro 5G 512GB")
					   .price(BigDecimal.valueOf(1629)).category(vegetables).build(),
				Product.builder().serial("SN1000-0002").name("Apple iPhone 12 Pro Max 5G 512GB")
					   .price(BigDecimal.valueOf(1749)).category(vegetables).build(),
				Product.builder().serial("SN1100-0001").name("Samsung Galaxy S21 Ultra")
					   .price(BigDecimal.valueOf(1479.99)).category(vegetables).build(),
				Product.builder().serial("SN1100-0002").name("Samsung Galaxy S20 Ultra")
					   .price(BigDecimal.valueOf(1199)).category(vegetables).build(),
				Product.builder().serial("SN1200-0001").name("Huawei P40 Pro")
					   .price(BigDecimal.valueOf(899.49)).category(vegetables).build(),
				Product.builder().serial("SN1300-0001").name("Xiaomi Redmi 9A")
					   .price(BigDecimal.valueOf(199.75)).category(vegetables).build(),
				Product.builder().serial("SN1400-0001").name("RealMe C11")
					   .price(BigDecimal.valueOf(129)).category(vegetables).build(),
				Product.builder().serial("SN1500-0001").name("Honor 10 Lite")
					   .price(BigDecimal.valueOf(179)).category(vegetables).build(),
				Product.builder().serial("SN1000-0003").name("Apple iPhone 12 Pro Max 5G 128GB")
					   .price(BigDecimal.valueOf(1339)).category(vegetables).build(),
				Product.builder().serial("SN1000-0004").name("Apple iPhone 11 Pro 256GB")
					   .price(BigDecimal.valueOf(1299.99)).category(vegetables).build()
										);

		products.forEach(productService::create);

		List<Customer> customers = customerService.createAll(
				Customer.builder().email("c.giannacoulis@codehub.gr")
						.firstname("Constantinos").lastname("Giannacoulis")
						.address("3583 Tennessee Avenue")
						.customerCategory(CustomerCategory.INDIVIDUAL).age(47).build(),
				Customer.builder().email("john.porter@gmailx.com")
						.firstname("John").lastname("Porter")
						.address("2955 Blackwell Street")
						.customerCategory(CustomerCategory.BUSINESS).age(40).build());

		customers.forEach(customerService::create);
		//@formatter:on

		final Order order = orderService.initiateOrder(customers.get(0));
		orderService.addItem(order, products.get(0), 1);
		orderService.addItem(order, products.get(1), 2);
		orderService.addItem(order, products.get(2), 3);
		orderService.checkout(order, PaymentMethod.CREDIT_CARD);

		final List<KeyValue<String, BigDecimal>> averageOrderCostPerCustomer = orderService.findAverageOrderCostPerCustomer();
		averageOrderCostPerCustomer.forEach(result -> logger.info("{}", result));

		final List<KeyValue<String, Long>> customersPurchasedMostExpensiveProduct = customerService.findCustomersPurchasedMostExpensiveProduct();
		customersPurchasedMostExpensiveProduct.forEach(result -> logger.info("{}", result));
	}
}
