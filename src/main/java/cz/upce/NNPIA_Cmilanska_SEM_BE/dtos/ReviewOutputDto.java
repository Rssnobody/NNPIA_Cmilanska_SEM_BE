package cz.upce.NNPIA_Cmilanska_SEM_BE.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewOutputDto {
    private Long reviewId;
    private LocalDateTime creationDate;
    private String text;
    private RatingOutputDto rating;
}
