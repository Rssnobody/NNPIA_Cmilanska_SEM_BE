package cz.upce.NNPIA_Cmilanska_SEM_BE.repositories;

import cz.upce.NNPIA_Cmilanska_SEM_BE.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IReviewRepository extends JpaRepository<Review, Long> {

    @Query(
    """
    SELECT review
    FROM Review review
    JOIN UserBook userBook ON userBook.review.reviewId = review.reviewId
    WHERE userBook.book.bookId = :bookId
    AND userBook.review IS NOT NULL
    """
    )
    List<Review> findAllBySpecifiedBook(Long bookId);
}
