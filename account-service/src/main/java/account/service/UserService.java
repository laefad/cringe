package account.service;

import account.model.User;

public interface UserService {

	User create(User user);
	User getUserByUsername(String username);

}
