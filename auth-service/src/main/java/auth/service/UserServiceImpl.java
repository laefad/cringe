package auth.service;

import auth.model.User;
import auth.repository.UserRepository;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserServiceImpl implements UserService {

	private final Logger log = LoggerFactory.getLogger(getClass());

	private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

	@Autowired
	private UserRepository userRepository;

	@Override
	public void create(User user) {

		User u = userRepository.findByUsername(user.getUsername());

		if (u != null)
			throw new IllegalArgumentException("user already exists: " + user.getUsername());

		user.setPassword(encoder.encode(user.getPassword()));
		userRepository.save(user);

		log.info("new user has been created: {}", user.getUsername());
	}

	@Override
	public User getUserByUsername(String username) {
		return userRepository.findByUsername(username);
	}

}
