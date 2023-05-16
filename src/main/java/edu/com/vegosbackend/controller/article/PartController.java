package edu.com.vegosbackend.controller.article;

import edu.com.vegosbackend.controller.settings.model.assembler.article.PartModelAssembler;
import edu.com.vegosbackend.controller.settings.model.dto.article.PartDTO;
import edu.com.vegosbackend.domain.main.article.Part;
import edu.com.vegosbackend.service.article.PartService;
import jakarta.validation.Valid;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("api/v1/articles/{current}/parts")
@Data
public class PartController {
    private final PartModelAssembler passembler;
    private final ModelMapper partMapper;
    private final PartService partService;

    @GetMapping
    public CollectionModel<EntityModel<PartDTO>> getAllPartsByArticle(@PathVariable Long current) {
        return CollectionModel.of(
                partService.getAllPartsByArticle(current)
                        .stream()
                        .map(val -> partMapper.map(val, PartDTO.class))
                        .map(passembler::toModel)
                        .collect(Collectors.toList()),
                linkTo(methodOn(PartController.class)
                        .getAllPartsByArticle(current))
                        .withSelfRel());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<PartDTO>> getPartById(@PathVariable Long current, @PathVariable Long id) {
        EntityModel<PartDTO> part = passembler
                .toModel(partMapper
                        .map(partService
                                .getPartByPartIdAndArticleId(current, id)
                                .get(), PartDTO.class));
        return ResponseEntity
                .created(part.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(part);
    }

    @PostMapping
    public ResponseEntity<?> addPartById(@PathVariable Long current, @Valid @RequestBody PartDTO part) {
        EntityModel<PartDTO> entityModel = passembler
                .toModel(partMapper
                        .map(partService
                                .addPartToArticleById(partMapper
                                        .map(part, Part.class), current)
                                .get(), PartDTO.class));
        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editPartById(@PathVariable Long current, @PathVariable Long id, @Valid @RequestBody PartDTO part) {
        EntityModel<PartDTO> entityModel = passembler
                .toModel(partMapper
                        .map(partService
                                .editPartByPartIdAndArticleId(partMapper
                                        .map(part, Part.class), id, current)
                                .get(), PartDTO.class));
        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePartById(@PathVariable Long current, @PathVariable Long id) {
        partService.deletePartByPartIdAndArticleId(id, current);
        return ResponseEntity
                .noContent()
                .build();
    }
}
