package gr.codelearn.spring.showcase.app.controller;

import gr.codelearn.spring.showcase.app.base.BaseMapper;
import gr.codelearn.spring.showcase.app.domain.Customer;
import gr.codelearn.spring.showcase.app.mapper.CustomerMapper;
import gr.codelearn.spring.showcase.app.service.BaseService;
import gr.codelearn.spring.showcase.app.service.CustomerService;
import gr.codelearn.spring.showcase.app.transfer.ApiResponse;
import gr.codelearn.spring.showcase.app.transfer.resource.CustomerResource;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/customers")
public class CustomerController extends AbstractController<Customer, CustomerResource> {
	private final CustomerService customerService;
	private final CustomerMapper customerMapper;

	@Override
	public BaseService<Customer, Long> getBaseService() {
		return customerService;
	}

	@Override
	public BaseMapper<Customer, CustomerResource> getMapper() {
		return customerMapper;
	}

	@GetMapping(params = {"email"})
	public ResponseEntity<ApiResponse<CustomerResource>> findByEmail(@RequestParam String email) {
		return ResponseEntity.ok(
				ApiResponse.<CustomerResource>builder().data(getMapper().toResource(customerService.findByEmail(email)))
						   .build());
	}
}
