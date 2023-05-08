package edu.com.vegosbackend.controller.model.assembler;

import edu.com.vegosbackend.controller.ArticleController;
import edu.com.vegosbackend.controller.model.dto.PartDTO;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class PartModelAssembler implements RepresentationModelAssembler<PartDTO, EntityModel<PartDTO>> {
    @Override
    public EntityModel<PartDTO> toModel(PartDTO entity) {
        return EntityModel.of(entity,
                WebMvcLinkBuilder.linkTo(methodOn(ArticleController.class).getPartById(
                                entity.getArticle().getId(),
                                entity.getId())).
                        withSelfRel(),
                linkTo(methodOn(ArticleController.class).getAllPartsByArticle(entity.getArticle().getId())).withRel("parts"));
    }
}
