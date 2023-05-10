package edu.com.vegosbackend.controller.settings.model.assembler.article;

import edu.com.vegosbackend.controller.article.ArticleController;
import edu.com.vegosbackend.controller.settings.model.dto.article.ArticleDTO;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ArticleModelAssembler implements RepresentationModelAssembler<ArticleDTO, EntityModel<ArticleDTO>> {
    @Override
    public EntityModel<ArticleDTO> toModel(ArticleDTO entity) {
        return EntityModel.of(entity,
                linkTo(methodOn(ArticleController.class).getArticleById(entity.getId())).withSelfRel(),
                linkTo(methodOn(ArticleController.class).getAllArticles()).withRel("articles"));
    }
}
