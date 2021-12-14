package book_service.controller;

import com.fasterxml.jackson.databind.node.ObjectNode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import book_service.model.Book;
import book_service.service.BookService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@RestController
@RequestMapping(
    path = "book"
)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookController {
    
    @Autowired
    BookService bookService;

    @GetMapping
    public Book getBookById(
        @RequestBody long bookId
    ) {
        return bookService.getBookById(bookId);
    }

    @PostMapping
    public Book saveBook(
        @RequestBody Book book
    ) {
        return bookService.saveBook(book);
    }

    @DeleteMapping
    public Book deleteBookById(
        @RequestBody long bookId
    ) {
        return bookService.deleteBookById(bookId);
    }

    @PatchMapping(path = "/addTag")
    public Book addTagToBook(
        @RequestBody ObjectNode data
    ) {
        long bookId = data.get("bookId").asLong();
        long tagId = data.get("tagId").asLong();

        return bookService.addTagToBookTagList(bookId, tagId);
    }

    @PatchMapping(path = "/removeTag")
    public Book removeTagFromBook(
        @RequestBody ObjectNode data
    ) {
        long bookId = data.get("bookId").asLong();
        long tagId = data.get("tagId").asLong();

        return bookService.removeTagFromBookTagList(bookId, tagId);
    }

    @PatchMapping(path = "/addAuthor")
    public Book addAuthorToBook(
        @RequestBody ObjectNode data
    ) {
        long bookId = data.get("bookId").asLong();
        long authorId = data.get("authorId").asLong();

        return bookService.addAuthorToBookById(bookId, authorId);
    }

    @PatchMapping(path = "/removeAuthor")
    public Book removeAuthorFromBook(
        @RequestBody ObjectNode data
    ) {
        long bookId = data.get("bookId").asLong();
        long authorId = data.get("authorId").asLong();

        return bookService.removeAuthorFromBookById(bookId, authorId);
    }

}
