package cz.upce.NNPIA_Cmilanska_SEM_BE.domain;

import cz.upce.NNPIA_Cmilanska_SEM_BE.dtos.AuthorOutputDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collections;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
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

    public Author(String firstName, String lastName, List<Book> books) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.books = books;
    }

    public AuthorOutputDto toDto() {
        return new AuthorOutputDto(
                getAuthorId(),
                getFirstName(),
                getLastName()
        );
    }
}
