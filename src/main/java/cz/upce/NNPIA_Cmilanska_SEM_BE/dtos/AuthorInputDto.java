package cz.upce.NNPIA_Cmilanska_SEM_BE.dtos;

import cz.upce.NNPIA_Cmilanska_SEM_BE.domain.Author;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collections;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorInputDto {
    private String firstName;
    private String lastName;

    public Author toEntity() {
        return new Author(
                getFirstName(),
                getLastName(),
                Collections.emptyList()
        );
    }
}
