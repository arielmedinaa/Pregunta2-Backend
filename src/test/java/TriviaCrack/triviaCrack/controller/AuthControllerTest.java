package TriviaCrack.triviaCrack.controller;

import TriviaCrack.triviaCrack.dto.LoginRequest;
import TriviaCrack.triviaCrack.dto.RegisterRequest;
import TriviaCrack.triviaCrack.modules.GamePanel.model.UsuariosMod;
import TriviaCrack.triviaCrack.service.AuthService;
import TriviaCrack.triviaCrack.service.JwtService;
import TriviaCrack.triviaCrack.service.UsuariosService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AuthController.class)
@Import({JwtService.class}) // Importamos JwtService real porque lo necesitamos para generar tokens
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private AuthService authService; // Mockeamos AuthService ya que es el que se prueba aquí indirectamente

    // Las siguientes dependencias son necesarias para que el contexto de Spring Security se cargue correctamente
    // aunque no las usemos directamente en las pruebas de AuthController si authService está mockeado.
    // Si quisiéramos probar la integración completa hasta SecurityConfig, necesitaríamos más mocks o una config más completa.
    @MockBean
    private UsuariosService usuariosService;
    @MockBean
    private PasswordEncoder passwordEncoder;
    @MockBean
    private AuthenticationManager authenticationManager;
    @MockBean
    private JwtService jwtServiceMock; // Mockeamos JwtService para controlar la generación de tokens en AuthService si no estuviera mockeado AuthService


    private RegisterRequest registerRequest;
    private LoginRequest loginRequest;
    private UsuariosMod sampleUser;
    private UserDetails sampleUserDetails;

    @BeforeEach
    void setUp() {
        registerRequest = RegisterRequest.builder()
                .username("testuser")
                .password("password123")
                .firstname("Test")
                .lastname("User")
                .build();

        loginRequest = LoginRequest.builder()
                .username("testuser")
                .password("password123")
                .build();

        sampleUser = UsuariosMod.builder()
                .id(1)
                .name("testuser")
                .password("hashedpassword") // Simula contraseña hasheada
                .isAdmin(false)
                .build();

        sampleUserDetails = new User("testuser", "hashedpassword", new ArrayList<>());
    }

    @Test
    void register_shouldReturnToken_whenRegistrationIsSuccessful() throws Exception {
        // Dado que AuthService.register devuelve un AuthResponse con un token
        given(authService.register(any(RegisterRequest.class)))
                .willReturn(new TriviaCrack.triviaCrack.dto.AuthResponse("mocked_jwt_token"));

        mockMvc.perform(post("/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(registerRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").value("mocked_jwt_token"));
    }

    @Test
    void login_shouldReturnToken_whenCredentialsAreValid() throws Exception {
        // Dado que AuthService.login devuelve un AuthResponse con un token
        given(authService.login(any(LoginRequest.class)))
                .willReturn(new TriviaCrack.triviaCrack.dto.AuthResponse("mocked_jwt_token"));

        // Simulación de que el usuario existe para JwtService (si no mockeamos authService completamente)
        given(usuariosService.findByName("testuser")).willReturn(Optional.of(sampleUser));
        given(jwtServiceMock.generateToken(any(UserDetails.class))).willReturn("mocked_jwt_token");


        mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").value("mocked_jwt_token"));
    }

    @Test
    @WithMockUser // Simula un usuario autenticado para probar un endpoint protegido (si tuviéramos uno aquí)
    void someProtectedEndpoint_shouldAllowAccess_whenUserIsAuthenticated() throws Exception {
        // Este es un ejemplo si tuvieras otro endpoint en AuthController que requiera autenticación
        // Por ejemplo, si /auth/me devolviera info del usuario actual.
        // given(authService.getCurrentUserInfo()).willReturn(someUserDto);
        // mockMvc.perform(get("/auth/me"))
        //        .andExpect(status().isOk());
    }
}
