package gr.codelearn.spring.showcase.app.repository;

public interface JokeRepository {
	String getSingleJoke(String... genres);
}
