package book_service.service;

import book_service.model.User;
import book_service.model.input.UserInput;
import book_service.repository.UserRepository;

import org.springframework.stereotype.Service;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserService {

    UserRepository userRepository;

    public User getUserById(long id) {
        return userRepository.getById(id);
    }

    public User createUser(UserInput input) {
        return userRepository.save(
            User.builder()
                .login(input.getLogin())
                .password(input.getPassword())
                .build()
        );
    }

    public User getUserByLogin(String login){
        return userRepository.findByLogin(login);
    }

}
