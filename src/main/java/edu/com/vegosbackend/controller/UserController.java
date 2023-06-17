package edu.com.vegosbackend.controller;

import edu.com.vegosbackend.controller.settings.model.assembler.UserModelAssembler;
import edu.com.vegosbackend.domain.main.user.User;
import edu.com.vegosbackend.service.UserService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("api/v1/users")
@Data
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    private final UserModelAssembler assembler;

    @GetMapping("/list")
    public CollectionModel<EntityModel<User>> getAllUsers() {
        return CollectionModel.of(
                userService.getAllUsers()
                        .stream()
                        .map(assembler::toModel)
                        .collect(Collectors.toList()),
                linkTo(methodOn(UserController.class).getAllUsers()).withSelfRel());
    }

    @GetMapping("/get/{id}")

    public ResponseEntity<EntityModel<User>> getUserById(@PathVariable Long id) {
        EntityModel<User> user = assembler.toModel(
                userService
                        .getUserById(id)
                        .orElseThrow()
        );
        return ResponseEntity
                .created(user.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(user);
    }

    @PostMapping("/add")
    public ResponseEntity<?> createUser(@RequestBody User user) {
        EntityModel<User> entityModel = assembler.toModel(
                userService
                        .createUser(user)
                        .orElseThrow());
        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<?> editUserById(@PathVariable Long id, @RequestBody User user) {
        EntityModel<User> entityModel = assembler.toModel(
                userService.updateUserById(user, id).orElseThrow());
        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.deleteUserById(id));
    }

    @DeleteMapping("/clear")
    private ResponseEntity<?> clear() {
        userService.clear();
        return ResponseEntity.noContent().build();
    }
}
