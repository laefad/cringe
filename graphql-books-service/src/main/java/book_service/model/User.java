package book_service.model;

import java.util.List;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Entity
@Table(name = "userEntity")
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Builder
@Getter
@Setter
@ToString
public class User {

    @Id
    @Column(nullable = false, unique = true)
    // @SequenceGenerator(
    //     name="pk_user_id_seq",
    //     allocationSize=1
    // )
    @GeneratedValue(
        strategy = GenerationType.IDENTITY//, 
        //generator="pk_user_id_seq"
    )
    long id;

    @Column(nullable = false)
    String login;

    @Column(nullable = false)
    String password;

    @Column
    String role;

    @ManyToMany(fetch = FetchType.LAZY)
    List<Book> books;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "user")
    List<UserBookTagList> tagLists;
}
