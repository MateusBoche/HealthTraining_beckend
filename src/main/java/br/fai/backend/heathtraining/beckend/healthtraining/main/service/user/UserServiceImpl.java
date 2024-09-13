package br.fai.backend.heathtraining.beckend.healthtraining.main.service.user;

import br.fai.backend.heathtraining.beckend.healthtraining.main.domain.UserModel;
import br.fai.backend.heathtraining.beckend.healthtraining.main.port.service.user.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {
    @Override
    public int add(UserModel entity) {
        return 0;
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public UserModel readById(int id) {
        return null;
    }

    @Override
    public List<UserModel> readAll() {
        return null;
    }

    @Override
    public void update(int id, UserModel entity) {

    }

    @Override
    public boolean updatePassword(int id, String newPassword) {
        return false;
    }
}
