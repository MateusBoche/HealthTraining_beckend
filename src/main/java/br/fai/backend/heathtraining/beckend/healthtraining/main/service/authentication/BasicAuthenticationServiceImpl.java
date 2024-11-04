package br.fai.backend.heathtraining.beckend.healthtraining.main.service.authentication;

import br.fai.backend.heathtraining.beckend.healthtraining.main.domain.UserModel;
import br.fai.backend.heathtraining.beckend.healthtraining.main.port.dao.user.UserDao;
import br.fai.backend.heathtraining.beckend.healthtraining.main.port.service.authentication.AuthenticationService;
import br.fai.backend.heathtraining.beckend.healthtraining.main.port.service.user.UserService;


public class BasicAuthenticationServiceImpl implements AuthenticationService {

    private final UserService userService;

    public BasicAuthenticationServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserModel authentication(String email, String password) {
        UserModel userModel = userService.findByEmail(email);
        if (userModel == null){
            return null;
        }

        if (!userModel.getPassword().equals(password)){
            return null;
        }
        return userModel;
    }
}
