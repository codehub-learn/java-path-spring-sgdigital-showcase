package gr.codelearn.spring.showcase.app.controller.demo;

import gr.codelearn.spring.showcase.app.service.demo.JokeService;
import gr.codelearn.spring.showcase.app.transfer.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/jokes")
public class JokeController {
	private final JokeService jokeService;

	@GetMapping("/**")
	public ResponseEntity<ApiResponse<String>> getSingleJoke() {
		return ResponseEntity.ok(ApiResponse.<String>builder().data(jokeService.getSingleJoke("Programming")).build());
	}

}
