package edu.com.vegosbackend.controller.course;

import edu.com.vegosbackend.controller.settings.model.assembler.course.SubThemeModelAssembler;
import edu.com.vegosbackend.controller.settings.model.assembler.course.ThemeModelAssembler;
import edu.com.vegosbackend.controller.settings.model.dto.course.structure.StructureSubThemeDTO;
import edu.com.vegosbackend.controller.settings.model.dto.course.structure.StructureThemeDTO;
import edu.com.vegosbackend.mapper.course.SubThemeMapper;
import edu.com.vegosbackend.mapper.course.ThemeMapper;
import edu.com.vegosbackend.service.course.StructureService;
import jakarta.validation.Valid;
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
@RequestMapping("api/v1/courses/{current}/themes")
@Data
public class StructureController {
    private final StructureService structureService;
    private final ThemeMapper themeMapper;
    private final SubThemeMapper subThemeMapper;
    private final ThemeModelAssembler assembler;
    private final SubThemeModelAssembler sassembler;

    @GetMapping
    public CollectionModel<EntityModel<StructureThemeDTO>> getAllThemesByCourse(@PathVariable Long current) {
        return CollectionModel.of(
                structureService.getAllThemesByCourseId(current)
                        .stream()
                        .map(themeMapper::convertToDTO)
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
                .toModel(themeMapper
                        .convertToDTO(structureService
                                .getThemeByThemeIdAndCourseId(current, id)
                                .get()));
        return ResponseEntity
                .created(theme.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(theme);
    }

    @PostMapping
    public ResponseEntity<?> addThemeById(@PathVariable Long current, @Valid @RequestBody StructureThemeDTO themeDTO) {
        EntityModel<StructureThemeDTO> entityModel = assembler
                .toModel(themeMapper
                        .convertToDTO(structureService
                                .addThemeToCourseById(themeMapper
                                        .convertToEntity(themeDTO), current)
                                .get()));
        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editThemeById(@PathVariable Long current, @PathVariable Long id, @Valid @RequestBody StructureThemeDTO themeDTO) {
        EntityModel<StructureThemeDTO> entityModel = assembler
                .toModel(themeMapper
                        .convertToDTO(structureService
                                .editThemeByThemeIdAndCourseId(themeMapper
                                        .convertToEntity(themeDTO), id, current)
                                .get()));
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
                        .map(subThemeMapper::convertToDTO)
                        .map(sassembler::toModel)
                        .collect(Collectors.toList()),
                linkTo(methodOn(StructureController.class)
                        .getAllSubThemesByCourseAndTheme(current, theme))
                        .withSelfRel());
    }

    @GetMapping("/{theme}/subthemes/{id}")
    public ResponseEntity<EntityModel<StructureSubThemeDTO>> getSubThemeById(@PathVariable Long current, @PathVariable Long theme, @PathVariable Long id) {
        EntityModel<StructureSubThemeDTO> subTheme = sassembler
                .toModel(subThemeMapper
                        .convertToDTO(structureService
                                .getSubThemeBySubThemeIdAndThemeIdAndCourseId(current, theme, id)
                                .get()));
        return ResponseEntity
                .created(subTheme.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(subTheme);
    }

    @PostMapping("/{theme}/subthemes")
    public ResponseEntity<?> addSubThemeById(@PathVariable Long current, @PathVariable Long theme, @Valid @RequestBody StructureSubThemeDTO themeDTO) {
        EntityModel<StructureSubThemeDTO> entityModel = sassembler
                .toModel(subThemeMapper
                        .convertToDTO(structureService
                                .addSubThemeToCourseByThemeIdAndCourseId(current, theme, subThemeMapper
                                        .convertToEntity(themeDTO)).get()));
        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @PutMapping("/{theme}/subthemes/{id}")
    public ResponseEntity<?> editSubThemeById(@PathVariable Long current, @PathVariable Long theme, @PathVariable Long id, @Valid @RequestBody StructureSubThemeDTO subThemeDTO) {
        EntityModel<StructureSubThemeDTO> entityModel = sassembler
                .toModel(subThemeMapper
                        .convertToDTO(structureService
                                .editSubThemeBySubThemeIdAndThemeIdAndCourseId(current, theme, id, subThemeMapper
                                        .convertToEntity(subThemeDTO))
                                .get()));
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
