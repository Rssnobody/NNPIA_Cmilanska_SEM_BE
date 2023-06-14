package cz.upce.NNPIA_Cmilanska_SEM_BE.domain;

import cz.upce.NNPIA_Cmilanska_SEM_BE.dtos.AuthorOutputDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collections;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "AUTHORS")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long authorId;
    @Column
    private String firstName;
    @Column
    private String lastName;
    @EqualsAndHashCode.Exclude
    @ManyToMany
    @JoinTable(
            name="AUTHOR_BOOKS",
            joinColumns = @JoinColumn(name="author_id"),
            inverseJoinColumns = @JoinColumn(name="book_id"))
    private List<Book> books = Collections.emptyList();

    public AuthorOutputDto toDto() {
        return new AuthorOutputDto(
                getAuthorId(),
                getFirstName(),
                getLastName()
        );
    }
}
