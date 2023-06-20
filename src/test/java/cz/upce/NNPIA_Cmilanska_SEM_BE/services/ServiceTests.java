package cz.upce.NNPIA_Cmilanska_SEM_BE.services;

import cz.upce.NNPIA_Cmilanska_SEM_BE.domain.Author;
import cz.upce.NNPIA_Cmilanska_SEM_BE.domain.Book;
import cz.upce.NNPIA_Cmilanska_SEM_BE.repositories.IAuthorRepository;
import cz.upce.NNPIA_Cmilanska_SEM_BE.repositories.IBookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ServiceTests {
    @Mock
    private IBookRepository bookRepository;

    @Mock
    private IAuthorRepository authorRepository;

    @InjectMocks
    private BookService bookService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        ReflectionTestUtils.setField(bookService, "bookRepository", bookRepository);
        ReflectionTestUtils.setField(bookService, "authorRepository", authorRepository);
    }

    @Test
    void create_NewBook_SuccessfullyCreated() throws ResourceAlreadyExists {
        // Arrange
        Book book = new Book();
        book.setName("Test Book");
        List<Author> authors = new ArrayList<>();
        authors.add(new Author("John", "Doe", Collections.emptyList()));
        authors.add(new Author("Jane", "Smith", Collections.emptyList()));
        book.setAuthors(authors);

        when(bookRepository.findBookByName(book.getName())).thenReturn(null);
        when(authorRepository.findAuthorByFirstNameAndLastName("John", "Doe")).thenReturn(null);
        when(authorRepository.findAuthorByFirstNameAndLastName("Jane", "Smith")).thenReturn(null);
        when(authorRepository.save(any(Author.class))).thenAnswer(invocation -> {
            Author savedAuthor = invocation.getArgument(0);
            savedAuthor.setAuthorId(1L); // Assign a mock ID for the saved author
            return savedAuthor;
        });
        when(bookRepository.save(book)).thenAnswer(invocation -> {
            Book savedBook = invocation.getArgument(0);
            savedBook.setBookId(1L); // Assign a mock ID for the saved book
            return savedBook;
        });

        // Act
        Book result = bookService.create(book);

        // Assert
        assertNotNull(result);
        assertEquals(1L, result.getBookId());
        assertEquals(2, result.getAuthors().size());
        verify(bookRepository, times(1)).findBookByName(book.getName());
        verify(authorRepository, times(2)).findAuthorByFirstNameAndLastName(anyString(), anyString());
        verify(authorRepository, times(2)).save(any(Author.class));
        verify(bookRepository, times(1)).save(book);
        verify(authorRepository, times(2)).createAuthorBook(anyLong(), eq(1L));
    }

    @Test
    void create_ExistingBook_ThrowsResourceAlreadyExists() {
        // Arrange
        Book book = new Book();
        book.setName("Test Book");
        when(bookRepository.findBookByName(book.getName())).thenReturn(new Book());

        // Act & Assert
        assertThrows(ResourceAlreadyExists.class, () -> bookService.create(book));
        verify(bookRepository, times(1)).findBookByName(book.getName());
        verify(authorRepository, never()).findAuthorByFirstNameAndLastName(anyString(), anyString());
        verify(authorRepository, never()).save(any(Author.class));
        verify(bookRepository, never()).save(any(Book.class));
        verify(authorRepository, never()).createAuthorBook(anyLong(), anyLong());
    }
}
