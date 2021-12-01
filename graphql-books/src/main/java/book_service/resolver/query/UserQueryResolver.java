package book_service.resolver.query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import book_service.model.User;
import book_service.service.UserService;
import graphql.kickstart.tools.GraphQLQueryResolver;


@Component
public class UserQueryResolver implements GraphQLQueryResolver {

    @Autowired
    UserService userService;

    User getUserByLogin(String login) {
        return userService.getUserByLogin(login);
    }

    User getUser(long id) {
        return userService.getUserById(id);
    }

}
