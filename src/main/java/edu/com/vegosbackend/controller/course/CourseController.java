package edu.com.vegosbackend.controller.course;

import edu.com.vegosbackend.controller.settings.model.assembler.course.CourseModelAssembler;
import edu.com.vegosbackend.controller.settings.model.dto.course.CourseDTO;
import edu.com.vegosbackend.mapper.course.CourseMapper;
import edu.com.vegosbackend.service.course.CourseService;
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
@RequestMapping("api/v1/courses")
@Data
@AllArgsConstructor
public class CourseController {
    private final CourseService courseService;
    private final CourseModelAssembler courseModelAssembler;
    private final CourseMapper courseMapper;

    @GetMapping
    public CollectionModel<EntityModel<CourseDTO>> getAllCourses() {
        return CollectionModel.of(courseService
                        .getAllCourses()
                        .stream()
                        .map(courseMapper::convertToDTO)
                        .map(courseModelAssembler::toModel)
                        .collect(Collectors.toList()),
                linkTo(methodOn(CourseController.class)
                        .getAllCourses())
                        .withSelfRel());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<CourseDTO>> getCourseById(@PathVariable Long id) {
        EntityModel<CourseDTO> course = courseModelAssembler
                .toModel(courseMapper
                        .convertToDTO(courseService
                                .getCourseById(id)
                                .get()));
        return ResponseEntity
                .created(course.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(course);
    }

    @PostMapping
    public ResponseEntity<?> createCourse(@Valid @RequestBody CourseDTO courseDTO) {
        EntityModel<CourseDTO> course = courseModelAssembler.toModel(courseMapper
                .convertToDTO(courseService
                        .createCourse(courseMapper
                                .convertToEntity(courseDTO))
                        .get()));
        return ResponseEntity
                .created(course.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(course);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editCourseById(@PathVariable Long id,@Valid @RequestBody CourseDTO courseDTO) {
        EntityModel<CourseDTO> entityModel = courseModelAssembler.toModel(courseMapper
                .convertToDTO(courseService
                        .updateCourseById(courseMapper
                                .convertToEntity(courseDTO), id)
                        .get()));
        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCourseById(@PathVariable Long id) {
        courseService.deleteCourseById(id);
        return ResponseEntity
                .noContent()
                .build();
    }
}
