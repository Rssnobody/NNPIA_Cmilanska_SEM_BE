package cz.upce.NNPIA_Cmilanska_SEM_BE.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import cz.upce.NNPIA_Cmilanska_SEM_BE.dtos.AuthorOutputDto;
import cz.upce.NNPIA_Cmilanska_SEM_BE.dtos.BookDetailsOutputDto;
import cz.upce.NNPIA_Cmilanska_SEM_BE.dtos.BookOutputDto;
import cz.upce.NNPIA_Cmilanska_SEM_BE.dtos.ReviewOutputDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "BOOKS")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookId;
    @Column
    private String name;
    @Column
    private String picture;
    @Column
    private String description;
    @EqualsAndHashCode.Exclude
    @ManyToMany(mappedBy = "books")
    private List<Author> authors = Collections.emptyList();
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    @OneToMany(mappedBy = "book")
    private List<UserBook> userBooks = Collections.emptyList();

    public Book(Long bookId, String name, String picture, String description, List<Author> authors) {
        this.bookId = bookId;
        this.name = name;
        this.picture = picture;
        this.description = description;
        this.authors = authors;
    }

    public Book(String name, String picture, String description, List<Author> authors, List<UserBook> userBooks) {
        this.name = name;
        this.picture = picture;
        this.description = description;
        this.authors = authors;
        this.userBooks = userBooks;
    }

    public BookOutputDto toDto() {
        List<AuthorOutputDto> authorDTOs
                = getAuthors().stream().map(Author::toDto).collect(Collectors.toList());

        return new BookOutputDto(
                getBookId(),
                getName(),
                getPicture(),
                getDescription(),
                authorDTOs
        );
    }

    public BookDetailsOutputDto toDetailsDto(List<Review> reviews) {
        List<AuthorOutputDto> authorDTOs
                = getAuthors().stream().map(Author::toDto).toList();

        List<ReviewOutputDto> reviewDTOs = reviews.stream().map(Review::toDto).toList();

        return new BookDetailsOutputDto(
                getBookId(),
                getName(),
                getPicture(),
                getDescription(),
                authorDTOs,
                reviewDTOs
        );
    }
}
