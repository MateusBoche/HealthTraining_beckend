package br.fai.backend.heathtraining.beckend.healthtraining.main.service.authentication;

import br.fai.backend.heathtraining.beckend.healthtraining.main.domain.UserModel;
import br.fai.backend.heathtraining.beckend.healthtraining.main.port.service.authentication.AuthenticationService;
import br.fai.backend.heathtraining.beckend.healthtraining.main.port.service.user.UserService;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

public class JwtAuthenticationServiceImpl implements AuthenticationService {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public JwtAuthenticationServiceImpl(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public UserModel authenticate(String email, String password) {
        final UserModel userModel = userService.findByEmail(email);
        if (userModel == null){
            throw  new UsernameNotFoundException("email nao encontrado");
        }

        if (!passwordEncoder.matches(password, userModel.getPassword())){
            throw new BadCredentialsException("credenciais invalidas");
        }

        return userModel;
    }
}
