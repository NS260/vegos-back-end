package edu.com.vegosbackend.controller.article;

import edu.com.vegosbackend.controller.settings.model.assembler.article.ArticleModelAssembler;
import edu.com.vegosbackend.controller.settings.model.dto.article.ArticleDTO;
import edu.com.vegosbackend.mapper.article.ArticleMapper;
import edu.com.vegosbackend.service.article.ArticleService;
import jakarta.validation.Valid;
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
@RequestMapping("api/v1/articles")
@Data
@AllArgsConstructor
public class ArticleController {
    private final ArticleService articleService;
    private final ArticleModelAssembler assembler;
    private final ArticleMapper articleMapper;

    @GetMapping
    public CollectionModel<EntityModel<ArticleDTO>> getAllArticles() {
        return CollectionModel.of(articleService
                        .getAllArticles()
                        .stream()
                        .map(articleMapper::convertToDTO)
                        .map(assembler::toModel)
                        .collect(Collectors.toList()),
                linkTo(methodOn(ArticleController.class)
                        .getAllArticles())
                        .withSelfRel());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<ArticleDTO>> getArticleById(@PathVariable Long id) {
        EntityModel<ArticleDTO> article = assembler
                .toModel(articleMapper
                        .convertToDTO(articleService
                                .getArticleById(id)
                                .get()));
        return ResponseEntity
                .created(article.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(article);
    }

    @PostMapping
    public ResponseEntity<?> createArticle(@Valid @RequestBody ArticleDTO article) {
        EntityModel<ArticleDTO> model = assembler.toModel(articleMapper
                .convertToDTO(articleService
                        .createArticle(articleMapper
                                .convertToEntity(article))
                        .get()));
        return ResponseEntity
                .created(model.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(model);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editArticleById(@PathVariable Long id, @Valid @RequestBody ArticleDTO article) {
        EntityModel<ArticleDTO> entityModel = assembler.toModel(articleMapper
                .convertToDTO(articleService
                        .updateArticleById(articleMapper
                                .convertToEntity(article), id)
                        .get()));
        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        articleService.deleteArticleById(id);
        return ResponseEntity
                .noContent()
                .build();
    }
}
