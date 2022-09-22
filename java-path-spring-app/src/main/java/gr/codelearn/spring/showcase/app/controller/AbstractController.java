package gr.codelearn.spring.showcase.app.controller;

import gr.codelearn.spring.showcase.app.base.BaseComponent;
import gr.codelearn.spring.showcase.app.domain.BaseModel;
import gr.codelearn.spring.showcase.app.service.BaseService;
import gr.codelearn.spring.showcase.app.transfer.ApiResponse;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.Valid;
import java.util.List;
import java.util.concurrent.TimeUnit;

public abstract class AbstractController<T extends BaseModel> extends BaseComponent {
	protected abstract BaseService<T, Long> getBaseService();

	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<T>> get(@PathVariable("id") final Long id) {
		return ResponseEntity.ok(ApiResponse.<T>builder().data(getBaseService().get(id)).build());
	}

	@GetMapping
	public ResponseEntity<ApiResponse<List<T>>> findAll() {
		return ResponseEntity.ok(ApiResponse.<List<T>>builder().data(getBaseService().findAll()).build());
	}

	@PostMapping
	public ResponseEntity<ApiResponse<T>> create(@Valid @RequestBody final T entity) {
		return new ResponseEntity<>(ApiResponse.<T>builder().data(getBaseService().create(entity)).build(),
									getNoCacheHeaders(), HttpStatus.CREATED);
	}

	@PutMapping
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void update(@Valid @RequestBody final T entity) {
		getBaseService().update(entity);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") final Long id) {
		getBaseService().deleteById(id);
	}

	@DeleteMapping
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@Valid @RequestBody final T entity) {
		if (getBaseService().exists(entity)) {
			getBaseService().delete(entity);
		}
	}

	protected CacheControl getCacheHeaders(int cacheDuration) {
		// https://www.imperva.com/learn/performance/cache-control/
		// Note: Header "Expires" is ignored in max-age is set.
		// response.addHeader("Cache-Control", "max-age=60, must-revalidate, no-transform");
		return CacheControl.maxAge(cacheDuration, TimeUnit.SECONDS).noTransform().mustRevalidate();
	}

	protected HttpHeaders getNoCacheHeaders() {
		final HttpHeaders headers = new HttpHeaders();
		// HTTP 1.1 cache control header
		headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
		// HTTP 1.0 cache control header
		headers.add("Pragma", "no-cache");
		headers.add("Expires", "0");
		return headers;
	}

	protected HttpHeaders getDownloadHeaders(final String filename) {
		final HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "attachment; filename=" + filename);
		return headers;
	}
}
