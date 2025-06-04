package fiap.kciao.apo_ia.usecases.services.security;

import fiap.kciao.apo_ia.domains.Usuario;
import fiap.kciao.apo_ia.usecases.services.query.UsuarioQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioDetailsServiceImpl implements UserDetailsService {

    private final UsuarioQueryService usuarioQueryService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = usuarioQueryService.findByEmailOrThrow(email);
        return new UsuarioDetailsImpl(usuario);
    }
}