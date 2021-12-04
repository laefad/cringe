package book_service.model;

import java.util.List;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;


@Entity
@Table(name = "userBookTagList")
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Builder
@Getter
@Setter
@ToString
public class UserBookTagList {

    @Id
    @Column(nullable = false, unique = true)
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE, 
        generator = "userBookTagList_seq"
    )
    @SequenceGenerator(
        name="userBookTagList_seq",
        sequenceName="seq_userBookTagList"
    )
    long id;

    @ManyToOne(fetch = FetchType.LAZY)
    User user;

    @ManyToOne(fetch = FetchType.LAZY)
    Book book;

    @OneToMany(fetch = FetchType.LAZY)
    List<Tag> tags;
}
