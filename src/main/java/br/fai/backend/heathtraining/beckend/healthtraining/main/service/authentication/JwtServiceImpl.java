package br.fai.backend.heathtraining.beckend.healthtraining.main.service.authentication;

import br.fai.backend.heathtraining.beckend.healthtraining.main.domain.UserModel;
import br.fai.backend.heathtraining.beckend.healthtraining.main.security.JwtService;
import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;
import java.util.Map;
import java.util.function.Function;

public class JwtServiceImpl implements JwtService {

    @Override
    public String getEmailFromToken(String token) {
        return "";
    }

    @Override
    public Date getExpirationDateFromToken(String token) {
        return null;
    }

    @Override
    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        return null;
    }

    @Override
    public Claims getAllClaimsFromToken(String token) {
        return null;
    }

    @Override
    public boolean isTokenExpired(String token) {
        return false;
    }

    @Override
    public boolean isTokenValid(String token, UserDetails userDetails) {
        return false;
    }

    @Override
    public String generateToken(UserDetails userDetails, String fullName, UserModel.UserRole role, String email) {
        return "";
    }

    @Override
    public String createToken(Map<String, Object> claims, String subject) {
        return "";
    }
}
