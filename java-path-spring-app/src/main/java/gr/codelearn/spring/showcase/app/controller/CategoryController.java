package gr.codelearn.spring.showcase.app.controller;

import gr.codelearn.spring.showcase.app.base.BaseMapper;
import gr.codelearn.spring.showcase.app.domain.Category;
import gr.codelearn.spring.showcase.app.mapper.CategoryMapper;
import gr.codelearn.spring.showcase.app.service.BaseService;
import gr.codelearn.spring.showcase.app.service.CategoryService;
import gr.codelearn.spring.showcase.app.transfer.ApiResponse;
import gr.codelearn.spring.showcase.app.transfer.resource.CategoryResource;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/categories")
public class CategoryController extends AbstractController<Category, CategoryResource> {
	private final CategoryService categoryService;
	private final CategoryMapper categoryMapper;

	@Override
	public BaseService<Category, Long> getBaseService() {
		return categoryService;
	}

	@Override
	public BaseMapper<Category, CategoryResource> getMapper() {
		return categoryMapper;
	}

	@GetMapping(params = {"description"})
	public ResponseEntity<ApiResponse<CategoryResource>> findByDescription(@RequestParam String description) {
		return ResponseEntity.ok(ApiResponse.<CategoryResource>builder().data(getMapper().toResource(
				categoryService.findByDescription(description))).build());
	}
}
