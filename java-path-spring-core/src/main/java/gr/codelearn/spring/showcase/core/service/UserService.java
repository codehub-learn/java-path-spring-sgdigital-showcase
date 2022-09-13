package gr.codelearn.spring.showcase.core.service;

import gr.codelearn.spring.showcase.core.domain.User;

import java.util.List;

public interface UserService {
	boolean register(User user);

	List<User> getUsers();

	User getUser(Long id);

	User getUserByUsername(String username);

	User getUserByEmail(String email);
}
