package TriviaCrack.triviaCrack.service;

import TriviaCrack.triviaCrack.modules.GamePanel.model.UsuariosMod;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UsuariosService usuariosService;

    public UserDetailsServiceImpl(UsuariosService usuariosService) {
        this.usuariosService = usuariosService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UsuariosMod usuario = usuariosService.findByName(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con nombre: " + username));

        // El objeto UsuariosMod ya implementa UserDetails, así que podemos devolverlo directamente.
        // Spring Security usará los métodos getUsername(), getPassword(), getAuthorities(), etc., de UsuariosMod.
        return usuario;
    }
}
