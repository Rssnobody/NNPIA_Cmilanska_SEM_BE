package cz.upce.NNPIA_Cmilanska_SEM_BE.domain;

import cz.upce.NNPIA_Cmilanska_SEM_BE.dtos.UserBookOutputDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
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

    public UserBook(AppUser user, Book book, State state, Review review) {
        this.user = user;
        this.book = book;
        this.state = state;
        this.review = review;
    }

    public UserBookOutputDto toDto() {
        return new UserBookOutputDto(
                getUserBookId(),
                getUser().toDto(),
                getBook().toDto(),
                getState().toDto(),
                getReview() == null ? null : getReview().toDto()
        );
    }
}
