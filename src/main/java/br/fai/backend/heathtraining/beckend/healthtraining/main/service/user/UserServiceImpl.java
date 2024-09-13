package br.fai.backend.heathtraining.beckend.healthtraining.main.service.user;

import br.fai.backend.heathtraining.beckend.healthtraining.main.domain.UserModel;
import br.fai.backend.heathtraining.beckend.healthtraining.main.port.dao.user.UserDao;
import br.fai.backend.heathtraining.beckend.healthtraining.main.port.service.user.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void update(int id, UserModel entity) {

    }

    @Override
    public boolean updatePassword(int id, String oldPassword, String newPassword) {
        return false;
    }

    @Override
    public int create(UserModel entity) {
        if(entity == null){
            return 0;
        }
        if (entity.getNomeCompleto().isEmpty() || entity.getSenha().isEmpty() || entity.getEmail().isEmpty()){
            return 0;
        }
        int id = userDao.add(entity);
        return id;
    }

    @Override
    public void delete(int id) {
        if(id<0){
            return;
        }
        userDao.remove(id);

    }

    @Override
    public UserModel findById(int id) {
        if(id<0){
            return null;
        }
        UserModel user = userDao.readById(id);
        return user;
    }

    @Override
    public List<UserModel> findAll() {
        List<UserModel> userModels = userDao.readAll();
        return userModels;
    }

    @Override
    public UserModel findByEmail(String email) {
        return null;
    }
}
