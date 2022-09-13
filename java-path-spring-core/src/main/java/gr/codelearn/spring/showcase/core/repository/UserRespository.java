package gr.codelearn.spring.showcase.core.repository;

import gr.codelearn.spring.showcase.core.domain.User;

import java.util.List;

public interface UserRespository {
	List<User> getUsers();

	User getUser(Long id);

	User getUserByUsername(String username);

	User getUserByEmail(String email);

	boolean save(User user);
}
