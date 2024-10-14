package br.fai.backend.heathtraining.beckend.healthtraining.main.controller;

import br.fai.backend.heathtraining.beckend.healthtraining.main.domain.UserModel;
import br.fai.backend.heathtraining.beckend.healthtraining.main.dto.UpdatePasswordDto;
import br.fai.backend.heathtraining.beckend.healthtraining.main.port.service.user.UserService;
import org.apache.catalina.User;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/user")
public class UserRestController {
    private final UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public ResponseEntity<List<UserModel>> getEntities(){
        List<UserModel> users = userService.findAll();
        return ResponseEntity.ok().body(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserModel> getEntityById(@PathVariable final int id){
        UserModel user = userService.findById(id);
        return ResponseEntity.ok().body(user);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<UserModel> getEntityByEmail(@PathVariable final String email){
        UserModel user = userService.findByEmail(email);
        return ResponseEntity.ok().body(user);
    }

    @PostMapping()
    public ResponseEntity<UserModel> createEntity(@RequestBody final UserModel data){
        int id = userService.create(data);
        final URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path(("/{id}"))
                .buildAndExpand(id)
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateEntity(@PathVariable final int id, @RequestBody final UserModel data){
        userService.update(id, data);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UserModel> deleteEntity(@PathVariable final int id){
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/update-password")
    public ResponseEntity<Void> updatePassword(@RequestBody final UpdatePasswordDto data){
        final boolean response = userService.updatePassword(data.getId(), data.getOldPassword(),data.getNewPassword());
        return response?
                ResponseEntity.ok().build()
                : ResponseEntity.badRequest().build();

    }

    @GetMapping("/{email}/{password}")
    public ResponseEntity<UserModel> authenticate(@PathVariable final String email, @PathVariable final String password){
        UserModel user = userService.authentication(email, password);
        if(user == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().body(user);
    }



}
