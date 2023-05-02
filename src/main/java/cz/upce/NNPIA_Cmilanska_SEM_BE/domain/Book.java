package cz.upce.NNPIA_Cmilanska_SEM_BE.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collections;
import java.util.List;

@Data
@NoArgsConstructor
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
    @OneToMany(mappedBy = "book")
    private List<UserBook> userBooks = Collections.emptyList();
}
