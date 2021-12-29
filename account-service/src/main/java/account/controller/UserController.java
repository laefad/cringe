package account.controller;

import account.model.User;
import account.model.UserInput;
import account.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.security.Principal;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@PreAuthorize("#oauth2.hasScope('server')")
	@GetMapping(path = "/{name}")
	public User getAccountByName(
		@PathVariable String name
	) {
		return userService.getUserByUsername(name);
	}
	
	@GetMapping(path = "/current")
	public User getCurrentAccount (
		Principal principal
	) {
		return userService.getUserByUsername(principal.getName());
	}

	// @PutMapping(path = "/current")
	// public void saveCurrentUser(
	// 	Principal principal, 
	// 	@Valid @RequestBody User user
	// ) {
	// 	accountService.save(principal.getName(), user);
	// }

	@PostMapping(path = "/")
	public User createNewAccount(
		@Valid @RequestBody UserInput user
	) {
		return userService.create(user);
	}

}
