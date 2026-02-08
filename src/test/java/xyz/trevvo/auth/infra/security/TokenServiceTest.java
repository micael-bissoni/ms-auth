package xyz.trevvo.auth.infra.security;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;
import xyz.trevvo.auth.domain.user.User;

import static org.junit.jupiter.api.Assertions.*;

class TokenServiceTest {

    private TokenService tokenService;
    private final String secret = "test-secret";

    @BeforeEach
    void setUp() {
        tokenService = new TokenService();
        ReflectionTestUtils.setField(tokenService, "secret", secret);
    }

    @Test
    void shouldGenerateToken() {
        User user = new User();
        user.setEmail("test@email.com");

        String token = tokenService.generateToken(user);

        assertNotNull(token);
        assertFalse(token.isEmpty());
    }

    @Test
    void shouldValidateToken() {
        User user = new User();
        user.setEmail("test@email.com");
        String token = tokenService.generateToken(user);

        String email = tokenService.validateToken(token);

        assertEquals(user.getEmail(), email);
    }

    @Test
    void shouldReturnNullWhenTokenIsInvalid() {
        String invalidToken = "invalid-token";

        String email = tokenService.validateToken(invalidToken);

        assertNull(email);
    }
}
