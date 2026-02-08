package xyz.trevvo.auth.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import xyz.trevvo.auth.domain.dto.LoginRequestDTO;
import xyz.trevvo.auth.domain.dto.RegisterRequestDTO;
import xyz.trevvo.auth.domain.user.User;
import xyz.trevvo.auth.infra.security.TokenService;
import xyz.trevvo.auth.repositories.UserRepository;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UserRepository userRepository;

    @MockitoBean
    private PasswordEncoder passwordEncoder;

    @MockitoBean
    private TokenService tokenService;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
    }

    @Test
    void shouldLoginSuccessfully() throws Exception {
        LoginRequestDTO loginRequest = new LoginRequestDTO("test@email.com", "password");
        User user = new User();
        user.setEmail("test@email.com");
        user.setPassword("encodedPassword");
        user.setName("Test User");

        when(userRepository.findByEmail(loginRequest.email())).thenReturn(Optional.of(user));
        when(passwordEncoder.matches(loginRequest.password(), user.getPassword())).thenReturn(true);
        when(tokenService.generateToken(user)).thenReturn("token");

        mockMvc.perform(post("/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(loginRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").value("token"))
                .andExpect(jsonPath("$.name").value("Test User"));
    }

    @Test
    void shouldReturnBadRequestWhenLoginFails() throws Exception {
        LoginRequestDTO loginRequest = new LoginRequestDTO("test@email.com", "wrongpassword");
        User user = new User();
        user.setEmail("test@email.com");
        user.setPassword("encodedPassword");

        when(userRepository.findByEmail(loginRequest.email())).thenReturn(Optional.of(user));
        when(passwordEncoder.matches(loginRequest.password(), user.getPassword())).thenReturn(false);

        mockMvc.perform(post("/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(loginRequest)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldRegisterSuccessfully() throws Exception {
        RegisterRequestDTO registerRequest = new RegisterRequestDTO("Test User", "test@email.com", "password");

        when(userRepository.findByEmail(registerRequest.email())).thenReturn(Optional.empty());
        when(passwordEncoder.encode(registerRequest.password())).thenReturn("encodedPassword");
        when(tokenService.generateToken(any(User.class))).thenReturn("token");

        mockMvc.perform(post("/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(registerRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").value("token"))
                .andExpect(jsonPath("$.name").value("Test User"));

        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void shouldReturnBadRequestWhenUserAlreadyExists() throws Exception {
        RegisterRequestDTO registerRequest = new RegisterRequestDTO("Test User", "test@email.com", "password");

        when(userRepository.findByEmail(registerRequest.email())).thenReturn(Optional.of(new User()));

        mockMvc.perform(post("/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(registerRequest)))
                .andExpect(status().isBadRequest());
    }
}
