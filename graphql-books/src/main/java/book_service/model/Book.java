package book_service.model;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "book")
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Builder
@Getter
@Setter
@ToString
public class Book {
    @Id
    @Column(nullable = false, unique = true)
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE, 
        generator = "book_seq"
    )
    @SequenceGenerator(
        name="book_seq",
        sequenceName="seq_book"
    )
    long id;

    @Column(nullable = false)
    String name;

    @Column()
    String description;

    @Column()
    @Temporal(TemporalType.DATE)
    Date publicationDate;

    @Column(nullable = false)
    String path;

    @ManyToMany(fetch = FetchType.LAZY)
    List<Author> authors;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "books")
    List<User> users;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "book")
    List<UserBookTagList> tagLists;
}
