package TriviaCrack.triviaCrack.service;

import TriviaCrack.triviaCrack.dto.AuthResponse;
import TriviaCrack.triviaCrack.dto.LoginRequest;
import TriviaCrack.triviaCrack.dto.RegisterRequest;
// Asumiendo que tienes una entidad User o Player y un servicio para manejarla
// import TriviaCrack.triviaCrack.model.User;
// import TriviaCrack.triviaCrack.model.Role; // Si tienes roles
// import TriviaCrack.triviaCrack.repository.UserRepository; // O tu capa de servicio/JDBC
import TriviaCrack.triviaCrack.modules.GamePanel.model.UsuariosMod;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final UserDetailsServiceImpl userDetailsServiceImpl; // Usar la implementación concreta
    private final AuthenticationManager authenticationManager;
    private final UsuariosService usuariosService; // Inyectar UsuariosService

    // Asegurarse que el constructor @RequiredArgsConstructor (de Lombok)
    // o uno explícito está presente y es correcto.
    // Si no se usa @RequiredArgsConstructor, el constructor explícito debe ser:
    /*
    public AuthService(PasswordEncoder passwordEncoder,
                       JwtService jwtService,
                       UserDetailsServiceImpl userDetailsServiceImpl,
                       AuthenticationManager authenticationManager,
                       UsuariosService usuariosService) {
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.userDetailsServiceImpl = userDetailsServiceImpl;
        this.authenticationManager = authenticationManager;
        this.usuariosService = usuariosService;
    }
    */

    public AuthResponse register(RegisterRequest request) {
        var user = UsuariosMod.builder()
                .name(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .isAdmin(false)
                .isOnline(false)
                .score(0)
                .isReady(false)
                .hasAnswered(false)
                .build();

        UsuariosMod savedUser = usuariosService.save(user);

        var jwtToken = jwtService.generateToken(savedUser);
        return AuthResponse.builder().token(jwtToken).build();
    }

    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        final UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(request.getUsername());
        var jwtToken = jwtService.generateToken(userDetails);
        return AuthResponse.builder().token(jwtToken).build();
    }
}
