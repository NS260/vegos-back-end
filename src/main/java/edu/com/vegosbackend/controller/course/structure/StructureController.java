package edu.com.vegosbackend.controller.course.structure;

import edu.com.vegosbackend.controller.settings.model.assembler.course.structure.SubThemeModelAssembler;
import edu.com.vegosbackend.controller.settings.model.assembler.course.structure.ThemeModelAssembler;
import edu.com.vegosbackend.controller.settings.model.dto.course.structure.StructureSubThemeDTO;
import edu.com.vegosbackend.controller.settings.model.dto.course.structure.StructureThemeDTO;
import edu.com.vegosbackend.domain.main.course.structure.StructureSubTheme;
import edu.com.vegosbackend.domain.main.course.structure.StructureTheme;
import edu.com.vegosbackend.service.course.structure.StructureService;
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
@RequestMapping("api/v1/courses/{current}/themes")
@Data
public class StructureController {
    private final StructureService structureService;
    private final ModelMapper mapper;
    private final ThemeModelAssembler assembler;
    private final SubThemeModelAssembler sassembler;

    @GetMapping
    public CollectionModel<EntityModel<StructureThemeDTO>> getAllThemesByCourse(@PathVariable Long current) {
        return CollectionModel.of(
                structureService.getAllThemesByCourseId(current)
                        .stream()
                        .map(val -> mapper.map(val, StructureThemeDTO.class))
                        .map(assembler::toModel)
                        .collect(Collectors.toList()),
                linkTo(methodOn(StructureController.class)
                        .getAllThemesByCourse(current))
                        .withSelfRel()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<StructureThemeDTO>> getThemeById(@PathVariable Long current, @PathVariable Long id) {
        EntityModel<StructureThemeDTO> theme = assembler
                .toModel(mapper
                        .map(structureService
                                .getThemeByThemeIdAndCourseId(current, id)
                                .get(), StructureThemeDTO.class));
        return ResponseEntity
                .created(theme.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(theme);
    }

    @PostMapping
    public ResponseEntity<?> addThemeById(@PathVariable Long current, @Valid @RequestBody StructureThemeDTO themeDTO) {
        EntityModel<StructureThemeDTO> entityModel = assembler
                .toModel(mapper
                        .map(structureService
                                .addThemeToCourseById(mapper
                                        .map(themeDTO, StructureTheme.class), current)
                                .get(), StructureThemeDTO.class));
        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editThemeById(@PathVariable Long current, @PathVariable Long id, @Valid @RequestBody StructureThemeDTO themeDTO) {
        EntityModel<StructureThemeDTO> entityModel = assembler
                .toModel(mapper
                        .map(structureService
                                .editThemeByThemeIdAndCourseId(mapper
                                        .map(themeDTO, StructureTheme.class), id, current)
                                .get(), StructureThemeDTO.class));
        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteThemeById(@PathVariable Long current, @PathVariable Long id) {
        structureService.deleteThemeByThemeIdAndCourseId(id, current);
        return ResponseEntity
                .noContent()
                .build();
    }

    @GetMapping("/{theme}/subthemes")
    public CollectionModel<EntityModel<StructureSubThemeDTO>> getAllSubThemesByCourseAndTheme(@PathVariable Long current, @PathVariable Long theme) {
        return CollectionModel.of(
                structureService.getAllSubThemesByThemeIdAndCourseId(current, theme)
                        .stream()
                        .map(val -> mapper.map(val, StructureSubThemeDTO.class))
                        .map(sassembler::toModel)
                        .collect(Collectors.toList()),
                linkTo(methodOn(StructureController.class)
                        .getAllSubThemesByCourseAndTheme(current, theme))
                        .withSelfRel());
    }

    @GetMapping("/{theme}/subthemes/{id}")
    public ResponseEntity<EntityModel<StructureSubThemeDTO>> getSubThemeById(@PathVariable Long current, @PathVariable Long theme, @PathVariable Long id) {
        EntityModel<StructureSubThemeDTO> subTheme = sassembler
                .toModel(mapper
                        .map(structureService
                                .getSubThemeBySubThemeIdAndThemeIdAndCourseId(current, theme, id)
                                .get(), StructureSubThemeDTO.class));
        return ResponseEntity
                .created(subTheme.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(subTheme);
    }

    @PostMapping("/{theme}/subthemes")
    public ResponseEntity<?> addSubThemeById(@PathVariable Long current, @PathVariable Long theme, @Valid @RequestBody StructureSubThemeDTO themeDTO) {
        EntityModel<StructureSubThemeDTO> entityModel = sassembler
                .toModel(mapper
                        .map(structureService
                                .addSubThemeToCourseByThemeIdAndCourseId(current, theme, mapper
                                        .map(themeDTO, StructureSubTheme.class))
                                .get(), StructureSubThemeDTO.class));
        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @PutMapping("/{theme}/subthemes/{id}")
    public ResponseEntity<?> editSubThemeById(@PathVariable Long current, @PathVariable Long theme, @PathVariable Long id, @Valid @RequestBody StructureSubThemeDTO subThemeDTO) {
        EntityModel<StructureSubThemeDTO> entityModel = sassembler
                .toModel(mapper
                        .map(structureService
                                .editSubThemeBySubThemeIdAndThemeIdAndCourseId(current, theme, id, mapper
                                        .map(subThemeDTO, StructureSubTheme.class))
                                .get(), StructureSubThemeDTO.class));
        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @DeleteMapping("/{theme}/subthemes/{id}")
    public ResponseEntity<?> deleteSubThemeById(@PathVariable Long current, @PathVariable Long theme, @PathVariable Long id) {
        structureService.deleteSubthemeBySubthemeIdAndThemeIdAndCourseId(current, theme, id);
        return ResponseEntity
                .noContent()
                .build();
    }
}
