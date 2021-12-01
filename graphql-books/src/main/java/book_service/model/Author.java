package book_service.model;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "author")
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Builder
@Getter
@Setter
@ToString
public class Author {

    @Id
    @Column(nullable = false, unique = true)
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE, 
        generator = "author_seq"
    )
    @SequenceGenerator(
        name="author_seq",
        sequenceName="seq_author"
    )
    long id;

    @Column(nullable = false)
    String name;

    @Column(nullable = false)
    String surname;

    @Column()
    String middlename;

    @Column()
    String description;

    @Column()
    @Temporal(TemporalType.DATE)
    Date birthDate;

    @Column()
    @Temporal(TemporalType.DATE)
    Date deathDate;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "authors")
    List<Book> books;
}
