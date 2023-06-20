package cz.upce.NNPIA_Cmilanska_SEM_BE.services;


import cz.upce.NNPIA_Cmilanska_SEM_BE.domain.Rating;
import cz.upce.NNPIA_Cmilanska_SEM_BE.domain.Review;
import cz.upce.NNPIA_Cmilanska_SEM_BE.dtos.ReviewInput;
import cz.upce.NNPIA_Cmilanska_SEM_BE.repositories.IRatingRepository;
import cz.upce.NNPIA_Cmilanska_SEM_BE.repositories.IReviewRepository;
import cz.upce.NNPIA_Cmilanska_SEM_BE.repositories.IUserBookRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class ReviewService {
    private final IReviewRepository reviewRepository;
    private final IRatingRepository ratingRepository;
    private final IUserBookRepository userBookRepository;

    @Transactional(readOnly = true)
    public Review findReviewById(Long id) throws ResourceNotFoundException {
        var result = reviewRepository.findById(id);

        if (result.isEmpty()) {
            throw new ResourceNotFoundException();
        }
        return result.get();
    }

    public Review createReview(final ReviewInput input) throws ResourceNotFoundException {
        Rating rating = ratingRepository.findRatingByValue(input.getRatingValue());

        if (rating == null) throw new ResourceNotFoundException();

        Review review = new Review(input.getCreationDate(), input.getText(), rating);
        var createdReview = reviewRepository.save(review);

        var userBook = userBookRepository.findUserBookByUser_UserIdAndBook_BookId(input.getUserId(), input.getBookId());

        if (userBook == null) throw new ResourceNotFoundException();

        userBook.setReview(createdReview);
        userBookRepository.save(userBook);

        return createdReview;
    }
}
