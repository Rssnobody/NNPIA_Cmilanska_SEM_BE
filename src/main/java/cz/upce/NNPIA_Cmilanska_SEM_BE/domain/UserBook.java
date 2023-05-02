package cz.upce.NNPIA_Cmilanska_SEM_BE.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "USER_BOOKS")
public class UserBook {
    @EmbeddedId
    private UserBookId id;
    @ManyToOne
    @MapsId("userId")
    private AppUser user;
    @ManyToOne
    @MapsId("bookId")
    private Book book;
    @ManyToOne
    @JoinColumn(name="state_id")
    private State state;
    @ManyToOne
    @JoinColumn(name="review_id")
    private Review review;
}
