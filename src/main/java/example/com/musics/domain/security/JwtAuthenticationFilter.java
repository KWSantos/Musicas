package example.com.musics.domain.security;

import java.io.IOException;
import java.util.Date;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import example.com.musics.common.ConversorDate;
import example.com.musics.domain.dto.user.LoginRequestDTO;
import example.com.musics.domain.dto.user.LoginResponseDTO;
import example.com.musics.domain.dto.user.UserResponseDTO;
import example.com.musics.domain.model.ErrorResponse;
import example.com.musics.domain.model.Users;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter{
    
    private AuthenticationManager authenticationManager;
    private JwtUtil jUtil;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager, JwtUtil jUtil) {
        super();
        this.authenticationManager = authenticationManager;
        this.jUtil = jUtil;
        setFilterProcessesUrl("/api/auth");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
        try {
            LoginRequestDTO login = new ObjectMapper().readValue(request.getInputStream(), LoginRequestDTO.class);
            UsernamePasswordAuthenticationToken authTOken = new UsernamePasswordAuthenticationToken(login.getEmail(), login.getPassword());
            Authentication auth = authenticationManager.authenticate(authTOken);
            return auth;
        } catch(BadCredentialsException ex) {
            throw new BadCredentialsException("Usuário ou senha incorretos!");
        } catch(Exception ex) {
            throw new InternalAuthenticationServiceException(ex.getMessage());
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException {
        Users user = (Users) authResult.getPrincipal();
        String token = jUtil.generateToken(authResult);
        UserResponseDTO userResponse = new UserResponseDTO();
        userResponse.setId(user.getId());
        userResponse.setName(user.getName());
        LoginResponseDTO loginResponseDTO = new LoginResponseDTO();
        loginResponseDTO.setToken("Bearer "+token);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.getWriter().write(new Gson().toJson(loginResponseDTO));
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException authFail) throws IOException, ServletException {
        String hourDate = ConversorDate.converseDateToTimeDate(new Date());
        ErrorResponse error = new ErrorResponse(hourDate, 401, "Unauthorized", authFail.getMessage());
        response.setStatus(401);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.getWriter().write(new Gson().toJson(error));
    }
}
