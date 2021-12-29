package account.service;

import account.model.User;
import account.model.UserInput;

public interface UserService {

	User create(UserInput user);
	User getUserByUsername(String username);

}
