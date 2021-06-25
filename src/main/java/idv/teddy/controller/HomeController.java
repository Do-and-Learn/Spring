package idv.teddy.controller;

import idv.teddy.payload.RegisterForm;
import idv.teddy.repository.UserDetailsRepository;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Data
public class HomeController {
    private final UserDetailsRepository userDetailsRepository;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/")
    private String hello() {
        return "hello";
    }

    @GetMapping("/register")
    public String getRegisterForm(RegisterForm registerForm) {
        return "registerForm";
    }

    @PostMapping("/register")
    public String processRegistration(RegisterForm registerForm) {
        userDetailsRepository.save(registerForm.toUser(passwordEncoder));
        return "redirect:/";
    }
}
