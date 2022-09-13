package gr.codelearn.spring.showcase.core.service;

import gr.codelearn.spring.showcase.core.domain.User;
import gr.codelearn.spring.showcase.core.repository.UserRespository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl extends BaseServiceImpl implements UserService {
	private final UserRespository userRespository;

	@Override
	public boolean register(User user) {
		return userRespository.save(user);
	}

	@Override
	public List<User> getUsers() {
		return userRespository.getUsers();
	}

	@Override
	public User getUser(Long id) {
		return userRespository.getUser(id);
	}

	@Override
	public User getUserByUsername(final String username) {
		return userRespository.getUserByUsername(username);
	}

	@Override
	public User getUserByEmail(final String email) {
		return userRespository.getUserByEmail(email);
	}

}
