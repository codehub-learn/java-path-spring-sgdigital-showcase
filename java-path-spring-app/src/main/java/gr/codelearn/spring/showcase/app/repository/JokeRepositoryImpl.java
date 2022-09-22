package gr.codelearn.spring.showcase.app.repository;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class JokeRepositoryImpl implements JokeRepository {
	private final RestTemplate restTemplate;

	@Value("${jokeapi.location}")
	private String jokeApiLocation;

	public String getSingleJoke(String... genres) {
		// This method is able to fetch jokes of "single" type, as described by the API which is in use.
		final ResponseEntity<JsonNode> jokeJSONResponse = restTemplate.getForEntity(buildUrl("single", genres),
																					JsonNode.class);
		return Objects.requireNonNull(jokeJSONResponse.getBody()).get("joke").textValue();
	}

	private URI buildUrl(String type, String... genres) {
		// Simple method to build our url, if more than one genre have been chosen
		//@formatter:off
		return UriComponentsBuilder.fromUriString(jokeApiLocation)
								   .pathSegment(String.join(",", genres))
								   .queryParam("type", type)
								   .build().toUri();
		//@formatter:on
	}
}
