package example.com.musics.domain.dto.user;

public class LoginResponseDTO {
    
    private String token;
    private UserResponseDTO userResponseDTO;
    
    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }
    public UserResponseDTO getUserResponseDTO() {
        return userResponseDTO;
    }
    public void setUserResponseDTO(UserResponseDTO userResponseDTO) {
        this.userResponseDTO = userResponseDTO;
    }
}
