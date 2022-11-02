package gr.codelearn.spring.showcase.app.controller;

import gr.codelearn.spring.showcase.app.base.BaseMapper;
import gr.codelearn.spring.showcase.app.domain.Order;
import gr.codelearn.spring.showcase.app.mapper.OrderMapper;
import gr.codelearn.spring.showcase.app.service.BaseService;
import gr.codelearn.spring.showcase.app.service.OrderService;
import gr.codelearn.spring.showcase.app.transfer.ApiResponse;
import gr.codelearn.spring.showcase.app.transfer.KeyValue;
import gr.codelearn.spring.showcase.app.transfer.PurchasesCostPerCustomerCategoryDto;
import gr.codelearn.spring.showcase.app.transfer.resource.OrderResource;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController extends AbstractController<Order, OrderResource> {
	private final OrderService orderService;
	private final OrderMapper orderMapper;

	@Override
	public BaseService<Order, Long> getBaseService() {
		return orderService;
	}

	@Override
	public BaseMapper<Order, OrderResource> getMapper() {
		return orderMapper;
	}

	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<OrderResource>> get(@PathVariable("id") final Long id) {
		return ResponseEntity.ok(
				ApiResponse.<OrderResource>builder().data(getMapper().toResource(orderService.getLazy(id))).build());
	}

	@GetMapping
	public ResponseEntity<ApiResponse<List<OrderResource>>> findAll() {
		return ResponseEntity.ok(
				ApiResponse.<List<OrderResource>>builder().data(getMapper().toResources(orderService.findAll()))
						   .build());
	}

	@GetMapping(headers = "r=findAverageOrderCostPerCustomer")
	public ResponseEntity<ApiResponse<List<KeyValue<String, BigDecimal>>>> findAverageOrderCostPerCustomer() {
		return ResponseEntity.ok(ApiResponse.<List<KeyValue<String, BigDecimal>>>builder()
											.data(orderService.findAverageOrderCostPerCustomer()).build());
	}

	@GetMapping(headers = "r=findTotalNumberOfOrdersAndCostOfPurchasesPerCustomerCategory")
	public ResponseEntity<ApiResponse<List<PurchasesCostPerCustomerCategoryDto>>> findTotalNumberOfOrdersAndCostOfPurchasesPerCustomerCategory() {
		return ResponseEntity.ok(ApiResponse.<List<PurchasesCostPerCustomerCategoryDto>>builder()
											.data(orderService.findTotalNumberOfOrdersAndCostOfPurchasesPerCustomerCategory())
											.build());
	}
}
