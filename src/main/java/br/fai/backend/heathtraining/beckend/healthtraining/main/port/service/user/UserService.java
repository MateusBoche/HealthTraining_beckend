package br.fai.backend.heathtraining.beckend.healthtraining.main.port.service.user;

import br.fai.backend.heathtraining.beckend.healthtraining.main.domain.UserModel;
import br.fai.backend.heathtraining.beckend.healthtraining.main.port.service.crud.CrudService;

public interface UserService extends CrudService<UserModel>, UpdatePasswordService{
}
