package cz.upce.NNPIA_Cmilanska_SEM_BE.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import cz.upce.NNPIA_Cmilanska_SEM_BE.domain.AuthRequest;
import cz.upce.NNPIA_Cmilanska_SEM_BE.domain.Book;
import cz.upce.NNPIA_Cmilanska_SEM_BE.dtos.BookOutputDto;
import cz.upce.NNPIA_Cmilanska_SEM_BE.security.JwtTokenProvider;
import cz.upce.NNPIA_Cmilanska_SEM_BE.services.AppUserDetailsService;
import cz.upce.NNPIA_Cmilanska_SEM_BE.services.AppUserService;
import cz.upce.NNPIA_Cmilanska_SEM_BE.services.BookService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class ControllerTests {
    @Mock
    private AppUserService appUserService;

    @Mock
    private AppUserDetailsService appUserDetailsService;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JwtTokenProvider jwtTokenProvider;

    @Mock
    private BookService bookService;

    @InjectMocks
    private BookController bookController;

    @InjectMocks
    private AuthController authController;
    private ObjectMapper objectMapper;

    private MockMvc mockMvc;
    private MockMvc bookMockMvc;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(authController).build();
        bookMockMvc = MockMvcBuilders.standaloneSetup(bookController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testCreateAuthenticationToken_WithValidCredentials_ReturnsToken() throws Exception {
        Mockito.when(authenticationManager.authenticate(Mockito.any(UsernamePasswordAuthenticationToken.class))).thenReturn(null);

        UserDetails userDetails = new User("username", "password", Collections.emptyList());
        Mockito.when(appUserDetailsService.loadUserByUsername(Mockito.anyString())).thenReturn(userDetails);

        String jwt = "mocked-jwt";
        Mockito.when(jwtTokenProvider.createToken(Mockito.any(UserDetails.class))).thenReturn(jwt);

        AuthRequest authRequest = new AuthRequest();
        authRequest.setUserName("username");
        authRequest.setPassword("password");

        mockMvc.perform(MockMvcRequestBuilders.post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(authRequest)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.jwt", Matchers.is(jwt)));
    }

    @Test
    public void testFindAllBooks() throws Exception {
        // Arrange
        List<Book> books = Arrays.asList(
                new Book(1L, "Book 1", "Picture 1", "Description 1", Collections.emptyList()),
                new Book(2L, "Book 2", "Picture 2", "Description 2", Collections.emptyList())
        );
        List<BookOutputDto> bookOutputDtos = books.stream().map(Book::toDto).toList();
        when(bookService.findAll()).thenReturn(books);

        // Act & Assert
        bookMockMvc.perform(get("/book"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(bookOutputDtos)));
    }

    @Test
    public void testFindBookById() throws Exception {
        // Arrange
        Book book = new Book(1L, "Book 1", "Picture 1", "Description 1", Collections.emptyList());
        BookOutputDto bookOutputDto = book.toDto();
        when(bookService.findBookById(1L)).thenReturn(book);

        // Act & Assert
        bookMockMvc.perform(get("/book/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(bookOutputDto)));
    }

    private String asJsonString(Object obj) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }
}
