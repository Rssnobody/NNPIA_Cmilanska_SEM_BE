package cz.upce.NNPIA_Cmilanska_SEM_BE.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserBookOutputDto {
    private AppUserOutputDto user;
    private BookOutputDto book;
    private StateOutputDto state;
    private ReviewOutputDto review;
}
