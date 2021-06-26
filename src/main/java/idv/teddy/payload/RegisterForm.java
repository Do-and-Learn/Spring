package idv.teddy.payload;

import idv.teddy.constraints.UniqueUsername;
import idv.teddy.domain.User;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.NotBlank;

@Data
public class RegisterForm {
    @NotBlank
    @UniqueUsername
    private final String username;

    @NotBlank
    private final String password;

    public User toUser(PasswordEncoder passwordEncoder) {
        return new User(username, passwordEncoder.encode(password));
    }
}
