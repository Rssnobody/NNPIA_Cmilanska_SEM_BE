package cz.upce.NNPIA_Cmilanska_SEM_BE.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookOutputDto {
    private Long bookId;
    private String name;
    private String picture;
    private String description;
    private List<AuthorOutputDto> authors = Collections.emptyList();
}
