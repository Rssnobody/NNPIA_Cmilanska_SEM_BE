package cz.upce.NNPIA_Cmilanska_SEM_BE.services;

import cz.upce.NNPIA_Cmilanska_SEM_BE.domain.Author;
import cz.upce.NNPIA_Cmilanska_SEM_BE.domain.Book;
import cz.upce.NNPIA_Cmilanska_SEM_BE.domain.Review;
import cz.upce.NNPIA_Cmilanska_SEM_BE.dtos.BookDetailsOutputDto;
import cz.upce.NNPIA_Cmilanska_SEM_BE.repositories.IAuthorRepository;
import cz.upce.NNPIA_Cmilanska_SEM_BE.repositories.IBookRepository;
import cz.upce.NNPIA_Cmilanska_SEM_BE.repositories.IReviewRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@AllArgsConstructor
public class BookService {
    private final IBookRepository bookRepository;
    private final IReviewRepository reviewRepository;
    private final IAuthorRepository authorRepository;

    @Transactional(readOnly = true)
    public Book findBookById(Long id) throws ResourceNotFoundException {
        var result = bookRepository.findBookByBookId(id);

        if (result == null) {
            throw new ResourceNotFoundException();
        }
        return result;
    }

    public Iterable<Book> findAll() {
        return bookRepository.findAll();
    }

    public Book findByBookName(String name) { return bookRepository.findBookByName(name); }

    public List<BookDetailsOutputDto> getBooksByUser(Long userId) throws ResourceNotFoundException {
        List<Book> books = bookRepository.findBooksOfSpecifiedUser(userId);

        return getBookDetailsOutputDtos(books);
    }

    public List<BookDetailsOutputDto> getBooksByStateAndUser(Long userId, Long state) {
        List<Book> books = bookRepository.findBooksByStateOfSpecifiedUser(userId, state);

        return getBookDetailsOutputDtos(books);
    }

    private List<BookDetailsOutputDto> getBookDetailsOutputDtos(List<Book> books) {
        if (books.isEmpty()) return Collections.emptyList();

        List<BookDetailsOutputDto> output = new ArrayList<>();
        for (var book: books) {
            List<Review> reviews = reviewRepository.findAllBySpecifiedBook(book.getBookId());
            output.add(book.toDetailsDto(reviews));
        }

        return output;
    }

    @Transactional
    public BookDetailsOutputDto getBookDetails(Long id) throws ResourceNotFoundException {
        Book book = findBookById(id);

        if (book == null) throw new ResourceNotFoundException();

        List<Review> reviews = reviewRepository.findAllBySpecifiedBook(book.getBookId());

        return book.toDetailsDto(reviews);
    }

    @Transactional
    public Book create(final Book book) throws ResourceAlreadyExists {
        Book existingBook = findByBookName(book.getName());
        if (existingBook != null) throw new ResourceAlreadyExists();

        List<Author> allAuthors = new ArrayList<>();
        List<Author> newAuthors = new ArrayList<>();

        for (var author : book.getAuthors()) {
            Author result = authorRepository.findAuthorByFirstNameAndLastName(author.getFirstName(), author.getLastName());
            if (result == null) {
                result = authorRepository.save(author);
                newAuthors.add(result);
            }
            allAuthors.add(result);
        }
        book.setAuthors(allAuthors);
        var newBook = bookRepository.save(book);

        for (var author: newAuthors) {
            authorRepository.createAuthorBook(author.getAuthorId(), book.getBookId());
        }

        return newBook;
    }
}
