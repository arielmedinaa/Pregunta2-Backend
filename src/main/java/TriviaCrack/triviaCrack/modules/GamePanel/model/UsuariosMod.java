package TriviaCrack.triviaCrack.modules.GamePanel.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UsuariosMod implements UserDetails { // Implementar UserDetails
    private int id;
    private String name; // Este sería el username
    private String password; // Nuevo campo para la contraseña hasheada
    private boolean isAdmin;
    private boolean isOnline;
    private int idTeam;

    private int score;
    private boolean isReady;
    private boolean hasAnswered;

    // Métodos de UserDetails
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Si isAdmin es true, asigna ROLE_ADMIN, sino ROLE_USER
        return List.of(new SimpleGrantedAuthority(isAdmin ? "ROLE_ADMIN" : "ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return name; // Usar el campo 'name' como username
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Puedes añadir lógica si las cuentas expiran
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Puedes añadir lógica para bloquear cuentas
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Puedes añadir lógica si las credenciales expiran
    }

    @Override
    public boolean isEnabled() {
        return true; // Puedes añadir lógica para deshabilitar cuentas
    }
}
