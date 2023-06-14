package cz.upce.NNPIA_Cmilanska_SEM_BE.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RatingOutputDto {
    private Long ratingId;
    private String name;
    private int value;
}
