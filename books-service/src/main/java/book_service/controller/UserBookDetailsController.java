package book_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import book_service.model.UserBookDetails;
import book_service.service.UserBookDetailsService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@RestController
@RequestMapping(
    path = "userBookDetails"
)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserBookDetailsController {

    @Autowired
    UserBookDetailsService userBookDetailsService;

    @GetMapping
    public UserBookDetails getUserBookDetails(
        @RequestParam String username
    ) {
        return userBookDetailsService.getUserBookDetailsByUsername(username);
    }

    @PostMapping
    public UserBookDetails createUserBookDetails(
        @RequestParam String username
    ) {
        return userBookDetailsService.createUserBookDetails(username);
    }

    @DeleteMapping
    public UserBookDetails removeUserBookDetails(
        @RequestParam String username
    ) {
        return userBookDetailsService.removeByUsername(username);
    }
}
