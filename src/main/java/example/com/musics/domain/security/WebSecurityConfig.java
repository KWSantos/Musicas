package example.com.musics.domain.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.*;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityConfig {
    
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AuthenticationConfiguration authenticationConfiguration;
    @Autowired
    private MyUserDetailsService userDetailsService;

    @Bean
    public UserDetailsService userDetailsService() {
        return new MyUserDetailsService();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http.headers().frameOptions().disable().and()
        .cors().and()
        .csrf().disable()
        .authorizeHttpRequests(auth -> auth
                .requestMatchers("/").hasAnyAuthority("USER", "ADMIN")
                .requestMatchers("/new").hasAnyAuthority("ADMIN")
                .requestMatchers("/edit/**").hasAnyAuthority("ADMIN")
                .requestMatchers("/delete/**").hasAuthority("ADMIN")
                .anyRequest().authenticated()
            )
            .formLogin(login -> login.permitAll())
            .logout(logout -> logout.permitAll())
            .exceptionHandling(eh -> eh.accessDeniedPage("/403"))
            ;
        http.addFilter(new JwtAuthenticationFilter(authenticationManager(authenticationConfiguration), jwtUtil));
        http.addFilter(new JwtAuthorizationFIlter(authenticationManager(authenticationConfiguration), jwtUtil, userDetailsService)); 
        return http.build();
    }
}
