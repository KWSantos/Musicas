package example.com.musics.security;

import java.io.IOException;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import example.com.musics.domain.model.Users;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter{

    private JwtUtil jwtUtil;
    private UserDetailsSecurityServe userDetailsSecurityServe;

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, JwtUtil jwtUtil, UserDetailsSecurityServe userDetailsSecurityServe) {
        super(authenticationManager);
        this.jwtUtil = jwtUtil;
        this.userDetailsSecurityServe = userDetailsSecurityServe;
    }
    
    private UsernamePasswordAuthenticationToken getAuthenticationToken(String token) {
        if (jwtUtil.isValidToken(token)) {
            String email = jwtUtil.getUserName(token);
            Users user = (Users) userDetailsSecurityServe.loadUserByUsername(email);
            return new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        }
        return null;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException{
        String header = request.getHeader("Authorization");
        if(header != null && header.startsWith("Bearer")){
            UsernamePasswordAuthenticationToken auth = getAuthenticationToken(header.substring(7));
            if(auth!=null && auth.isAuthenticated()){
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }
        chain.doFilter(request, response);
    }
}
