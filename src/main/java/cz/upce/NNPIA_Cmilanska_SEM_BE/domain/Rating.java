package cz.upce.NNPIA_Cmilanska_SEM_BE.domain;

import cz.upce.NNPIA_Cmilanska_SEM_BE.dtos.RatingOutputDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collections;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "RATINGS")
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ratingId;
    @Column
    private String name;
    @Column
    private int value;
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "rating")
    private List<Review> reviews = Collections.emptyList();

    public RatingOutputDto toDto() {
        return new RatingOutputDto(
                getRatingId(),
                getName(),
                getValue()
        );
    }
}
