package ec.com.leodev.supermarket.domain.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class SuperMarketUserDetailsService implements UserDetailsService {
    private final String username;
    private final String password;

    // Usuario único de la demo: viene de la configuración (SUPERMARKET_USER y SUPERMARKET_PASSWORD)
    public SuperMarketUserDetailsService(@Value("${supermarket.security.username}") String username,
                                         @Value("${supermarket.security.password}") String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (!this.username.equals(username)) {
            throw new UsernameNotFoundException("Usuario no encontrado: " + username);
        }
        return new User(this.username, "{noop}" + password, new ArrayList<>());
    }
}
