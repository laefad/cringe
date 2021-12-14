package book_service.resolver;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import book_service.model.Author;
import book_service.model.Book;
import book_service.model.Tag;
import book_service.model.UserBookDetails;
import book_service.model.input.book.BookCreateInput;
import book_service.service.AuthorService;
import book_service.service.BookService;
import book_service.service.TagService;
import book_service.service.UserBookDetailsService;

@Controller
public class BookResolver {
    
    @Autowired
    BookService bookService;

    @Autowired
    UserBookDetailsService userBookDetailsService;

    @Autowired
    TagService tagService;

    @Autowired
    AuthorService authorService;

    @QueryMapping
    public List<Book> getAllBooks() {
        return bookService.getAll();
    }

    @QueryMapping
    public Book getBook(@Argument long bookId) {
        return bookService.getBookById(bookId);
    }

    @MutationMapping
    public Book createBook(@Argument BookCreateInput input) {

        //TODO catch error
        UserBookDetails userBookDetails = 
                userBookDetailsService.getOrCreateByUsername(input.getUsername()); // TODO replace to only get

        if (userBookDetails == null)
            return null; // TODO add error 

        Set<Author> authors = null;

        if (input.getAuthorIds() != null)
            authors = input.getAuthorIds().stream()
                    .map(authorService::getAuthorById) //TODO add checks ?
                    .filter(Objects::nonNull)
                    .collect(Collectors.toSet());

        Set<Tag> tags = null;

        if (input.getTagIds() != null)
            tags = input.getTagIds().stream()
                    .map(tagService::getTagById) //TODO add checks ?
                    .filter(Objects::nonNull)
                    .collect(Collectors.toSet());

        Book book = Book.builder()
                        .name(input.getName())
                        .description(input.getDescription())
                        .users(
                            Set.of(userBookDetails)
                        )
                        .publicationDate(input.getPublicationDate())
                        .tags(tags)
                        .authors(authors)
                        .build();

        return bookService.saveBook(book);
    }

    @MutationMapping
    public Book deleteBook(@Argument long bookId) {
        return bookService.deleteBookById(bookId);
    }
}
