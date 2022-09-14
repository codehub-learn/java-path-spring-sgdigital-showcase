package gr.codelearn.spring.showcase.core.repository.pooling;

import gr.codelearn.spring.showcase.core.base.BaseComponent;
import gr.codelearn.spring.showcase.core.domain.User;
import gr.codelearn.spring.showcase.core.repository.DataRepository;
import gr.codelearn.spring.showcase.core.repository.UserRespository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

@Repository
@Primary
public class UserRepositoryImplSQLWithConnectionPooling extends BaseComponent
		implements UserRespository, DataRepository {
	@Override
	public void logData(String command) {
		// Code for logging data
		logger.info("{}: logging data", this.getClass().getName());
	}

	@Override
	public boolean save(User user) {
		// Code for saving
		logger.info("{}: saving user", this.getClass().getName());
		return true;
	}

	@Override
	public List<User> getUsers() {
		// Code for getting all users
		logger.info("{}: fetching all users", this.getClass().getName());
		return Collections.emptyList();
	}

	@Override
	public User getUser(Long id) {
		// Code for getting a user by id
		logger.info("{}: fetching user by id", this.getClass().getName());
		return null;
	}

	@Override
	public User getUserByUsername(String username) {
		// Code for getting a user by username
		logger.info("{}: fetching user by username", this.getClass().getName());
		return null;
	}

	@Override
	public User getUserByEmail(final String email) {
		// Code for getting a user by username
		logger.info("{}: fetching user by email", this.getClass().getName());
		return null;
	}
}
