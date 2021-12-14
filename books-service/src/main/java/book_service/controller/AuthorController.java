package book_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import book_service.model.Author;
import book_service.service.AuthorService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@RestController
@RequestMapping(
    path = "author"
)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthorController {

    @Autowired
    AuthorService authorService;

    @GetMapping
    public Author getAuthorById(
        @RequestParam long authorId
    ) {
        return authorService.getAuthorById(authorId);
    }

    @PostMapping
    public Author createAuthor(
        @RequestBody Author author
    ) {
        return authorService.saveAuthor(author);
    }

    @DeleteMapping
    public Author deleteAuthor(
        @RequestParam long authorId
    ) {
        return authorService.deleteAuthorById(authorId);
    }

}
