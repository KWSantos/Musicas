package example.com.musics.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import example.com.musics.domain.model.Users;
import example.com.musics.domain.repository.UserRepository;


@Component
public class UserDetailsSecurityServe implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Users> optUser = userRepository.findByEmail(username);
        if(optUser.isEmpty()) {
            throw new UsernameNotFoundException("Usuário ou senha incorretos");
        }
        return optUser.get();
    }
    
}
