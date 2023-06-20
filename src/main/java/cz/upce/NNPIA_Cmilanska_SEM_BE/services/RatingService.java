package cz.upce.NNPIA_Cmilanska_SEM_BE.services;

import cz.upce.NNPIA_Cmilanska_SEM_BE.domain.Rating;
import cz.upce.NNPIA_Cmilanska_SEM_BE.repositories.IRatingRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class RatingService {
    private final IRatingRepository ratingRepository;

    @Transactional(readOnly = true)
    public Rating findRatingById(Long id) throws ResourceNotFoundException {
        var result = ratingRepository.findById(id);

        if (result.isEmpty()) {
            throw new ResourceNotFoundException();
        }
        return result.get();
    }

    public Rating findByValue(int value) {
        return ratingRepository.findRatingByValue(value);
    }

}
