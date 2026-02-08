package xyz.trevvo.auth.domain;

import org.junit.jupiter.api.Test;
import xyz.trevvo.auth.domain.dto.LoginRequestDTO;
import xyz.trevvo.auth.domain.dto.LoginResponseDTO;
import xyz.trevvo.auth.domain.dto.RegisterRequestDTO;
import xyz.trevvo.auth.domain.dto.RegisterResponseDTO;
import xyz.trevvo.auth.domain.user.User;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class DomainTests {

    @Test
    void testUserEntity() {
        User user = new User();
        user.setId("1");
        user.setName("Test");
        user.setEmail("test@email.com");
        user.setPassword("pass");

        assertEquals("1", user.getId());
        assertEquals("Test", user.getName());
        assertEquals("test@email.com", user.getEmail());
        assertEquals("pass", user.getPassword());
    }

    @Test
    void testDTOs() {
        LoginRequestDTO loginRequest = new LoginRequestDTO("email", "pass");
        assertEquals("email", loginRequest.email());
        assertEquals("pass", loginRequest.password());

        LoginResponseDTO loginResponse = new LoginResponseDTO("name", "token");
        assertEquals("name", loginResponse.name());
        assertEquals("token", loginResponse.token());

        RegisterRequestDTO registerRequest = new RegisterRequestDTO("name", "email", "pass");
        assertEquals("name", registerRequest.name());
        assertEquals("email", registerRequest.email());
        assertEquals("pass", registerRequest.password());

        RegisterResponseDTO registerResponse = new RegisterResponseDTO("name", "token");
        assertEquals("name", registerResponse.name());
        assertEquals("token", registerResponse.token());
    }
}
