package cz.upce.NNPIA_Cmilanska_SEM_BE.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorOutputDto {
    private Long authorId;
    private String firstName;
    private String lastName;
}
