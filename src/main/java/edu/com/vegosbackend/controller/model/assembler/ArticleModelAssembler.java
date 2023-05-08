package edu.com.vegosbackend.controller.model.assembler;

import edu.com.vegosbackend.controller.ArticleController;
import edu.com.vegosbackend.controller.model.dto.ArticleDTO;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ArticleModelAssembler implements RepresentationModelAssembler<ArticleDTO, EntityModel<ArticleDTO>> {
    public EntityModel<ArticleDTO> toModel(ArticleDTO entity) {
        return EntityModel.of(entity,
                linkTo(methodOn(ArticleController.class).getArticleById(entity.getId())).withSelfRel(),
                linkTo(methodOn(ArticleController.class).getAllArticles()).withRel("articles"));
    }
}
