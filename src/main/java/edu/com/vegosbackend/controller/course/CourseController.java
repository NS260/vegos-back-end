package edu.com.vegosbackend.controller.course;

import com.turkraft.springfilter.boot.Filter;
import edu.com.vegosbackend.controller.settings.model.assembler.course.CourseModelAssembler;
import edu.com.vegosbackend.controller.settings.model.dto.course.CourseDTO;
import edu.com.vegosbackend.domain.main.course.Course;
import edu.com.vegosbackend.service.course.CourseService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.domain.Specification;
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
    private final ModelMapper courseMapper;
    @GetMapping
    public CollectionModel<EntityModel<CourseDTO>> getAllCourses() {
        return CollectionModel.of(courseService
                        .getAllCourses()
                        .stream()
                        .map(val -> courseMapper.map(val, CourseDTO.class))
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
                        .map(courseService
                                .getCourseById(id)
                                .get(), CourseDTO.class));
        return ResponseEntity
                .created(course.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(course);
    }
    @PostMapping
    @CrossOrigin
    public ResponseEntity<?> createCourse(@Valid @RequestBody CourseDTO courseDTO) {
        EntityModel<CourseDTO> course = courseModelAssembler.toModel(courseMapper
                .map(courseService
                        .createCourse(courseMapper
                                .map(courseDTO, Course.class))
                        .get(), CourseDTO.class));
        return ResponseEntity
                .created(course.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(course);
    }
    @PutMapping("/{id}")
    @CrossOrigin
    public ResponseEntity<EntityModel<CourseDTO>> editCourseById(@PathVariable Long id, @Valid @RequestBody CourseDTO courseDTO) {
        EntityModel<CourseDTO> entityModel = courseModelAssembler.toModel(courseMapper
                .map(courseService
                        .updateCourseById(courseMapper
                                .map(courseDTO, Course.class), id)
                        .get(), CourseDTO.class));
        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }
    @DeleteMapping("/{id}")
    @CrossOrigin
    public ResponseEntity<?> deleteCourseById(@PathVariable Long id) {
        courseService.deleteCourseById(id);
        return ResponseEntity
                .noContent()
                .build();
    }
    @GetMapping("/search")
    public CollectionModel<EntityModel<CourseDTO>> search(@Filter Specification<CourseDTO> specification) {
        return CollectionModel.of(courseService
                        .searchBySpecification((Specification<Course>) courseMapper.map(specification, Specification.class))
                        .stream()
                        .map(val -> courseMapper.map(val, CourseDTO.class))
                        .map(courseModelAssembler::toModel)
                        .collect(Collectors.toList()),
                linkTo(methodOn(CourseController.class)
                        .search(specification))
                        .withSelfRel());
    }
}
