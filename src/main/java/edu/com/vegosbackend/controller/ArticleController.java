package edu.com.vegosbackend.controller;

import edu.com.vegosbackend.controller.model.assembler.ArticleModelAssembler;
import edu.com.vegosbackend.controller.model.assembler.PartModelAssembler;
import edu.com.vegosbackend.controller.model.dto.ArticleDTO;
import edu.com.vegosbackend.controller.model.dto.PartDTO;
import edu.com.vegosbackend.mapper.article.ArticleMapper;
import edu.com.vegosbackend.mapper.article.PartMapper;
import edu.com.vegosbackend.service.ArticleService;
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
    private final PartModelAssembler passembler;
    private final ArticleMapper articleMapper;
    private final PartMapper partMapper;

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
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/parts")
    public CollectionModel<EntityModel<PartDTO>> getAllPartsByArticle(@PathVariable Long id) {
        return CollectionModel.of(
                articleService.getAllPartsByArticle(id)
                        .stream()
                        .map(partMapper::convertToDTO)
                        .map(passembler::toModel)
                        .collect(Collectors.toList()),
                linkTo(methodOn(ArticleController.class).getAllPartsByArticle(id)).withSelfRel());
    }

    @GetMapping("/{current}/parts/{id}")
    public ResponseEntity<EntityModel<PartDTO>> getPartById(@PathVariable Long current, @PathVariable Long id) {
        EntityModel<PartDTO> part = passembler
                .toModel(partMapper
                        .convertToDTO(articleService
                                .getPartByPartIdAndArticleId(current, id)
                                .get()));
        return ResponseEntity
                .created(part.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(part);
    }

    @PostMapping("/{id}/parts")
    public ResponseEntity<?> addPartById(@PathVariable Long id, @Valid @RequestBody PartDTO part) {
        EntityModel<PartDTO> entityModel = passembler
                .toModel(partMapper
                        .convertToDTO(articleService
                                .addPartToArticleById(partMapper
                                        .convertToEntity(part), id)
                                .get()));
        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @PutMapping("/{current}/parts/{id}")
    public ResponseEntity<?> editPartById(@PathVariable Long current, @PathVariable Long id, @Valid @RequestBody PartDTO part) {
        EntityModel<PartDTO> entityModel = passembler
                .toModel(partMapper
                        .convertToDTO(articleService
                                .editPartByPartIdAndArticleId(partMapper
                                        .convertToEntity(part), id, current)
                                .get()));
        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @DeleteMapping("/{current}/parts/{id}")
    public ResponseEntity<?> deletePartById(@PathVariable Long current, @PathVariable Long id) {
        articleService.deletePartByPartIdAndArticleId(id, current);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/clear")
    public ResponseEntity<?> clear(){
        articleService.clear();
        return ResponseEntity.noContent().build();
    }
}
