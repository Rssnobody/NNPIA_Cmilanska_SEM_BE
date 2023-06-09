package cz.upce.NNPIA_Cmilanska_SEM_BE.domain;

import cz.upce.NNPIA_Cmilanska_SEM_BE.dtos.ReviewOutputDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "REVIEWS")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewId;
    @Column
    @EqualsAndHashCode.Exclude
    private LocalDateTime creationDate;
    @Column
    private String text;
    @ManyToOne
    @JoinColumn(name="rating_id")
    private Rating rating;
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "review")
    private List<UserBook> userBooks = Collections.emptyList();

    public Review(LocalDateTime creationDate, String text, Rating rating) {
        this.creationDate = creationDate;
        this.text = text;
        this.rating = rating;
    }

    public ReviewOutputDto toDto() {
        return new ReviewOutputDto(
                getReviewId(),
                getCreationDate(),
                getText(),
                getRating().toDto()
        );
    }
}
