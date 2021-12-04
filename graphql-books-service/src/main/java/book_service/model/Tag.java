package book_service.model;

import javax.persistence.*;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "tag")
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Builder
@Getter
@Setter
@ToString
public class Tag {

    @Id
    @Column(nullable = false, unique = true)
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE, 
        generator = "tag_seq"
    )
    @SequenceGenerator(
        name="tag_seq",
        sequenceName="seq_tag"
    )
    long id;

    @Column(nullable = false)
    String name;
    
}
