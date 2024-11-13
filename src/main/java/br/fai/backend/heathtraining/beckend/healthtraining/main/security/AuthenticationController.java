package br.fai.backend.heathtraining.beckend.healthtraining.main.security;

import br.fai.backend.heathtraining.beckend.healthtraining.main.domain.UserModel;
import br.fai.backend.heathtraining.beckend.healthtraining.main.dto.JwtTokenDto;
import br.fai.backend.heathtraining.beckend.healthtraining.main.port.service.authentication.AuthenticationService;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Profile("sec")
@RestController()
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    private final JwtService jwtService;

    private final UserDetailsService userDetailsService;

    public AuthenticationController(AuthenticationService authenticationService, JwtService jwtService, UserDetailsService userDetailsService) {
        this.authenticationService = authenticationService;
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }

    @PostMapping("/authenticate")
    public ResponseEntity<JwtTokenDto> authenticate(@RequestBody AuthenticationRequest authenticationRequest){
        String email = authenticationRequest.getEmail();
        String password = authenticationRequest.getPassword();

        UserModel authenticatedUser = authenticationService.authenticate(email, password);

        if (authenticatedUser == null){
            throw new BadCredentialsException("Invalid email or password");
        }

        UserDetails userDetails = userDetailsService.loadUserByUsername(email);
        if(userDetails == null){
            throw new UsernameNotFoundException("Email nao encontrado");
        }

        final String jwtToken = jwtService.generateToken(userDetails, authenticatedUser.getFullName(), authenticatedUser.getRole(), authenticatedUser.getEmail());

        if(jwtToken == null || jwtToken.isEmpty()){
            throw new InternalError("Jwt invalido");
        }
        System.out.println("jwt criado: " + jwtToken);

        final JwtTokenDto tokenDto = new JwtTokenDto(jwtToken);

        return ResponseEntity.ok(tokenDto);
    }
}
