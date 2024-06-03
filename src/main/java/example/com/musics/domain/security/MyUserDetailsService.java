package example.com.musics.domain.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import example.com.musics.domain.model.Users;
import example.com.musics.domain.repository.UserRepository;

public class MyUserDetailsService implements UserDetailsService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Users> user = userRepository.findByEmail(username);

        if(user.isEmpty()) {
            throw new UsernameNotFoundException("Usuario nao encontrado");
        }

        return user.get();
    }
    
}
