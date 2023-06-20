package cz.upce.NNPIA_Cmilanska_SEM_BE.services;

import cz.upce.NNPIA_Cmilanska_SEM_BE.domain.Author;
import cz.upce.NNPIA_Cmilanska_SEM_BE.repositories.IAuthorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthorService {
    private final IAuthorRepository authorRepository;

    public Author findAuthorById(Long id) throws ResourceNotFoundException {
        var result = authorRepository.findById(id);

        if (result.isEmpty()) throw new ResourceNotFoundException();

        return result.get();
    }

    public Author findByFirstNameAndLastName(String firstName, String lastName) {
        return authorRepository.findAuthorByFirstNameAndLastName(firstName, lastName);
    }

    public Author create(Author author) {
        return authorRepository.save(author);
    }
}
