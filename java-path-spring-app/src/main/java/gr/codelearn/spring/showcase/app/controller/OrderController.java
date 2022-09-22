package gr.codelearn.spring.showcase.app.controller;

import gr.codelearn.spring.showcase.app.domain.Order;
import gr.codelearn.spring.showcase.app.service.BaseService;
import gr.codelearn.spring.showcase.app.service.OrderService;
import gr.codelearn.spring.showcase.app.transfer.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController extends AbstractController<Order> {
	private final OrderService orderService;

	@Override
	public BaseService<Order, Long> getBaseService() {
		return orderService;
	}

	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<Order>> get(@PathVariable("id") final Long id) {
		return ResponseEntity.ok(ApiResponse.<Order>builder().data(orderService.get(id)).build());
	}

	@GetMapping
	public ResponseEntity<ApiResponse<List<Order>>> findAll() {
		return ResponseEntity.ok(ApiResponse.<List<Order>>builder().data(orderService.findAll()).build());
	}
}
