package cz.upce.NNPIA_Cmilanska_SEM_BE.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Getter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class UserBookId implements Serializable {
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "book_id")
    private Long bookId;
}
