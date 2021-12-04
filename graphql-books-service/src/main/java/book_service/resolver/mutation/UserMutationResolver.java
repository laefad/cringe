package book_service.resolver.mutation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import book_service.model.User;
import book_service.model.input.UserInput;
import book_service.service.UserService;
import graphql.kickstart.tools.GraphQLMutationResolver;


@Component
public class UserMutationResolver implements GraphQLMutationResolver {

    @Autowired
    UserService userService;

    User createUser(UserInput input) {
        return userService.createUser(input);
    }
}
