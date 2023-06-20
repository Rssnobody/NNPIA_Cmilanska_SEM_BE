package cz.upce.NNPIA_Cmilanska_SEM_BE.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewInput {
    private LocalDateTime creationDate;
    private String text;
    private int ratingValue;
    private Long userId;
    private Long bookId;
}
