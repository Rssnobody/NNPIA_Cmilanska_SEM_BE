package cz.upce.NNPIA_Cmilanska_SEM_BE.controllers;

import cz.upce.NNPIA_Cmilanska_SEM_BE.domain.Book;
import cz.upce.NNPIA_Cmilanska_SEM_BE.dtos.AuthorInputDto;
import cz.upce.NNPIA_Cmilanska_SEM_BE.dtos.BookInputDto;
import cz.upce.NNPIA_Cmilanska_SEM_BE.dtos.BookOutputDto;
import cz.upce.NNPIA_Cmilanska_SEM_BE.services.BookService;
import cz.upce.NNPIA_Cmilanska_SEM_BE.services.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/book")
@AllArgsConstructor
public class BookController {
    private final BookService bookService;
    protected final Log logger = LogFactory.getLog(getClass());

    @GetMapping("")
    public ResponseEntity<?> findAll() {
        List<BookOutputDto> result = new ArrayList<>();
        bookService.findAll().forEach(book -> result.add(book.toDto()));

        return ResponseEntity.ok(result);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> findBookById(@PathVariable final Long id) throws ResourceNotFoundException {
        var result = bookService.findBookById(id);
        return ResponseEntity.ok(result.toDto());
    }

    @GetMapping(path = "/details/{id}")
    public ResponseEntity<?> getBookDetailsById(@PathVariable final Long id) throws ResourceNotFoundException {
        var result = bookService.getBookDetails(id);
        return ResponseEntity.ok(result);
    }

    @GetMapping(path = "/user-books/{userId}")
    public ResponseEntity<?> getAllBooksOfUser(@PathVariable final Long userId) throws ResourceNotFoundException {
        var result = bookService.getBooksByUser(userId);
        return ResponseEntity.ok(result);
    }

    @GetMapping(path = "/want-to-read/{userId}")
    public ResponseEntity<?> getWantToReadBooksOfUser(@PathVariable final Long userId) {
        var result = bookService.getBooksByStateAndUser(userId, 1L);
        return ResponseEntity.ok(result);
    }

    @GetMapping(path = "/reading/{userId}")
    public ResponseEntity<?> getReadingBooksOfUser(@PathVariable final Long userId) {
        var result = bookService.getBooksByStateAndUser(userId, 2L);
        return ResponseEntity.ok(result);
    }

    @GetMapping(path = "/read/{userId}")
    public ResponseEntity<?> getReadBooksOfUser(@PathVariable final Long userId) {
        var result = bookService.getBooksByStateAndUser(userId, 3L);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createNewBook(@RequestBody BookInputDto book) throws Exception {
        var result = bookService.create(toEntity(book));
        return ResponseEntity.ok(result);
    }

    private Book toEntity(BookInputDto dto) {
        var authors = dto.getAuthors().stream().map(AuthorInputDto::toEntity).toList();
        return new Book(
                dto.getName(),
                dto.getPicture(),
                dto.getDescription(),
                authors,
                Collections.emptyList()
        );
    }
}
