package edu.com.vegosbackend.controller.settings.model.assembler;

import edu.com.vegosbackend.controller.UserController;
import edu.com.vegosbackend.domain.main.user.User;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class UserModelAssembler implements RepresentationModelAssembler<User, EntityModel<User>> {
    @Override
    public EntityModel<User> toModel(User entity) {
        return EntityModel.of(entity,
                linkTo(methodOn(UserController.class).getUserById(entity.getId())).withSelfRel(),
                linkTo(methodOn(UserController.class).getAllUsers()).withRel("users"));
    }
}
