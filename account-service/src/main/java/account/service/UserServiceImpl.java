package account.service;

import account.client.AuthServiceClient;
import account.model.User;
import account.model.UserInput;
import account.repository.UserRepository;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	AuthServiceClient authServiceClient;

	@Override
	public User create(UserInput userInput) {

		User user = userRepository.findByUsername(userInput.getUsername());

		if (user != null)
			throw new IllegalArgumentException("user already exists: " + userInput.getUsername());

		user = User.builder()
		           .username(userInput.getUsername())
				   .build();
		
		authServiceClient.createUser(userInput);
		return userRepository.save(user);
	}

	@Override
	public User getUserByUsername(String username) {
		return userRepository.findByUsername(username);
	}

}
