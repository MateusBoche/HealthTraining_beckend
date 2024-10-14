package br.fai.backend.heathtraining.beckend.healthtraining.main.dao.fake;

import br.fai.backend.heathtraining.beckend.healthtraining.main.domain.UserModel;
import br.fai.backend.heathtraining.beckend.healthtraining.main.port.dao.user.UserDao;

import java.util.ArrayList;
import java.util.List;

public class UserFakeDaoImpl implements UserDao {
    private static List<UserModel> users = new ArrayList<>();
    private static  int ID = 0;
    private int getNextId(){
        ID+=1;
        return ID;
    }

    public UserFakeDaoImpl(){
        System.out.println("instancia de user fake dao obitida");
        UserModel user1 = new UserModel();
        user1.setId(getNextId());
        user1.setEmail("Tiburssinho@gmail.com");
        user1.setFullName("Tibursinho tibursios");
        user1.setPassword("1234");

        UserModel user2 = new UserModel();
        user2.setId(getNextId());
        user2.setEmail("diltinho@gmail.com");
        user2.setFullName("diltinho diltussios");
        user2.setPassword("1234");


        UserModel user3 = new UserModel();
        user3.setId(getNextId());
        user3.setEmail("marquinho@gmail.com");
        user3.setFullName("marquinho marquitus");
        user3.setPassword("1234");

        UserModel user4 = new UserModel();
        user4.setId(getNextId());
        user4.setEmail("toninho@gmail.com");
        user4.setFullName("toninho toninhus");
        user4.setPassword("1234");

        users.add(user1);
        users.add(user2);
        users.add(user3);
        users.add(user4);

    }
    @Override
    public int add(UserModel entity) {
        final int id = getNextId();
        entity.setId(id);

        users.add(entity);
        return id;
    }

    @Override
    public void remove(int id) {
        int itemIndex = -1;

        for(int i = 0; i< users.size(); i++){
            UserModel user = users.get(i);
            if(user.getId()==id){
                itemIndex = 1;
                break;
            }
        }

        if(itemIndex == -1){
            return;
        }

        UserModel removedEntity = users.remove(itemIndex);
        System.out.println("O usuario " + removedEntity.getFullName() + " foi removido. Id do usuario removido: " + removedEntity.getId());

    }

    @Override
    public UserModel readById(int id) {
        for(UserModel user: users){
            if(user.getId() ==id){
                return user;
            }
        }

        return null;
    }

    @Override
    public List<UserModel> readAll() {

        return users;
    }

    @Override
    public void updateInformation(int id, UserModel entity) {
        UserModel user = readById(id);
        user.setFullName(entity.getFullName());

    }

    @Override
    public UserModel readByEmail(String email) {
        for(UserModel user: users){
            if(user.getEmail().equalsIgnoreCase(email)){
                return user;
            }
        }
        return null;
    }

    @Override
    public boolean updatePassword(int id, String newPassword) {
        UserModel user = readById(id);
        user.setPassword(newPassword);
        return true;
    }


}
