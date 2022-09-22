package gr.codelearn.spring.showcase.app.controller;

import gr.codelearn.spring.showcase.app.domain.Category;
import gr.codelearn.spring.showcase.app.service.BaseService;
import gr.codelearn.spring.showcase.app.service.CategoryService;
import gr.codelearn.spring.showcase.app.transfer.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/categories")
public class CategoryController extends AbstractController<Category> {
	private final CategoryService categoryService;

	@Override
	public BaseService<Category, Long> getBaseService() {
		return categoryService;
	}

	@GetMapping(params = {"description"})
	public ResponseEntity<ApiResponse<Category>> findByDescription(@RequestParam String description) {
		return ResponseEntity.ok(
				ApiResponse.<Category>builder().data(categoryService.findByDescription(description)).build());
	}
}
