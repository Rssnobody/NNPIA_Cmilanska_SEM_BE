package cz.upce.NNPIA_Cmilanska_SEM_BE.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserBookStateInputDto {
    private Long userBookId;
    private Long userId;
    private Long bookId;
    private Long stateId;
    private Long reviewId;
}
