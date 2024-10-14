package br.fai.backend.heathtraining.beckend.healthtraining.main.service.authentication;

import br.fai.backend.heathtraining.beckend.healthtraining.main.domain.UserModel;
import br.fai.backend.heathtraining.beckend.healthtraining.main.port.dao.user.UserDao;
import br.fai.backend.heathtraining.beckend.healthtraining.main.port.service.authentication.AuthenticationService;


public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserDao userDao;

    public AuthenticationServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserModel authentication(String email, String senha) {

        UserModel user = userDao.readByEmail(email);
        if (user == null || !user.getPassword().equals(senha)){
            throw new RuntimeException("Email ou senha inválido");
        }
        return user;
    }
}
