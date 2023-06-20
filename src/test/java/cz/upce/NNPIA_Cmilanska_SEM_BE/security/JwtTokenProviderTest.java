package cz.upce.NNPIA_Cmilanska_SEM_BE.security;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class JwtTokenProviderTest {
    private JwtTokenProvider jwtTokenProvider;

    @Mock
    private UserDetails userDetails;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        jwtTokenProvider = new JwtTokenProvider();
        jwtTokenProvider.secret = "secret-key";
    }

    @Test
    public void testCreateToken() {
        when(userDetails.getUsername()).thenReturn("silvia");
        String token = jwtTokenProvider.createToken(userDetails);
        assertNotNull(token);
    }

    @Test
    public void testGetUserNameFromToken() {
        String username = "silvia";
        when(userDetails.getUsername()).thenReturn(username);
        String token = jwtTokenProvider.createToken(userDetails);

        String extractedUsername = jwtTokenProvider.getUserNameFromToken(token);

        assertEquals(username, extractedUsername);
    }

    @Test
    public void testValidateTokenValid() {
        String username = "silvia";
        when(userDetails.getUsername()).thenReturn(username);
        String token = jwtTokenProvider.createToken(userDetails);

        JwtTokenProvider jwtTokenProviderMock = Mockito.spy(jwtTokenProvider);

        when(jwtTokenProviderMock.getUserNameFromToken(token)).thenReturn(username);
        when(jwtTokenProviderMock.isTokenExpired(token)).thenReturn(false);
        assertTrue(jwtTokenProviderMock.validateToken(token, userDetails));
    }

    @Test
    public void testValidateTokenInvalid() {
        String username = "silvia";
        when(userDetails.getUsername()).thenReturn(username);
        String token = jwtTokenProvider.createToken(userDetails);

        JwtTokenProvider jwtTokenProviderMock = Mockito.spy(jwtTokenProvider);

        when(jwtTokenProviderMock.getUserNameFromToken(token)).thenReturn("anotherUser");
        when(jwtTokenProviderMock.isTokenExpired(token)).thenReturn(false);
        assertFalse(jwtTokenProviderMock.validateToken(token, userDetails));
    }
}
