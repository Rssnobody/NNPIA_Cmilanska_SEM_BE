package cz.upce.NNPIA_Cmilanska_SEM_BE.domain;

import cz.upce.NNPIA_Cmilanska_SEM_BE.dtos.UserBookOutputDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "USER_BOOKS")
public class UserBook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userBookId;
    @ManyToOne
    @JoinColumn(name="user_id")
    private AppUser user;
    @ManyToOne
    @JoinColumn(name="book_id")
    private Book book;
    @ManyToOne
    @JoinColumn(name="state_id")
    private State state;
    @ManyToOne
    @JoinColumn(name="review_id")
    private Review review;

    public UserBookOutputDto toDto() {
        return new UserBookOutputDto(
                getUser().toDto(),
                getBook().toDto(),
                getState().toDto(),
                getReview() == null ? null : getReview().toDto()
        );
    }
}
