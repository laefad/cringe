package auth.service.security;

import auth.model.User;
import auth.service.UserService;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	UserService userService;

	@Override
	public UserDetails loadUserByUsername(
		String username
	) throws UsernameNotFoundException {
        User user = userService.getUserByUsername(username);

        if (user == null)
            throw new UsernameNotFoundException("User " + username + " not found in database");

		return user;
	}
}
