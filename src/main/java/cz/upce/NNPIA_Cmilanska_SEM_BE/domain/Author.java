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
    @ManyToMany(mappedBy = "authors")
    private List<Book> books = Collections.emptyList();
}
