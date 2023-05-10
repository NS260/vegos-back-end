package edu.com.vegosbackend.controller.settings.model.assembler.article;

import edu.com.vegosbackend.controller.article.PartController;
import edu.com.vegosbackend.controller.settings.model.dto.article.PartDTO;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class PartModelAssembler implements RepresentationModelAssembler<PartDTO, EntityModel<PartDTO>> {
    @Override
    public EntityModel<PartDTO> toModel(PartDTO entity) {
        return EntityModel.of(entity,
                linkTo(methodOn(PartController.class).getPartById(entity.getArticle().getId(), entity.getId())).withSelfRel(),
                linkTo(methodOn(PartController.class).getAllPartsByArticle(entity.getArticle().getId())).withRel("parts"));
    }
}
