package com.cibertec.denticore.security.config;

import com.cibertec.denticore.security.entities.Usuario;
import com.cibertec.denticore.security.entities.UsuarioRol;
import com.cibertec.denticore.security.repositories.UsuarioRepository;
import com.cibertec.denticore.security.repositories.UsuarioRolRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioRolRepository usuarioRolRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByNombreUsuario(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));

        List<UsuarioRol> rolesActivos = usuarioRolRepository.findByIdUsuario(usuario.getId());

        List<SimpleGrantedAuthority> authorities = rolesActivos.stream()
                .map(usuarioRol -> new SimpleGrantedAuthority("ROLE_" + usuarioRol.getRol().getNombre()))
                .toList();

        return new CustomUserDetails(usuario, authorities);
    }
}
