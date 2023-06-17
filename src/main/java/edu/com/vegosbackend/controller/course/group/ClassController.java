package edu.com.vegosbackend.controller.course.group;

import edu.com.vegosbackend.controller.settings.model.assembler.course.group.ClassModelAssembler;
import edu.com.vegosbackend.controller.settings.model.dto.course.group.ClassDTO;
import edu.com.vegosbackend.domain.main.course.group.Class;
import edu.com.vegosbackend.domain.main.user.roles.Student;
import edu.com.vegosbackend.service.course.group.ClassService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("api/v1/courses/{current}/classes")
@Data
@AllArgsConstructor
public class ClassController {
    private final ClassService service;
    private final ModelMapper classMapper;
    private final ClassModelAssembler cassembler;

    @GetMapping
    public CollectionModel<EntityModel<ClassDTO>> getAllClassesByCourseId(@PathVariable Long current) {
        return CollectionModel.of(service
                        .getAllClassesByCourseId(current)
                        .stream()
                        .map(val -> classMapper.map(val, ClassDTO.class))
                        .map(cassembler::toModel)
                        .collect(Collectors.toList()),
                linkTo(methodOn(ClassController.class)
                        .getAllClassesByCourseId(current))
                        .withSelfRel());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<ClassDTO>> getClassByClassIdAndCourseId(@PathVariable Long current, @PathVariable Long id) {
        EntityModel<ClassDTO> entityModel = cassembler
                .toModel(classMapper
                        .map(service
                                .getClassByClassIdAndCourseId(id, current)
                                .get(), ClassDTO.class));
        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @PostMapping
    public ResponseEntity<?> addClassToCourseById(@PathVariable Long current, @Valid @RequestBody ClassDTO classDTO) {
        EntityModel<ClassDTO> entityModel = cassembler
                .toModel(classMapper
                        .map(service
                                .addClassToCourseById(classMapper
                                        .map(classDTO, Class.class), current)
                                .get(), ClassDTO.class));
        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editClassByClassIdAndCourseId(@PathVariable Long current, @PathVariable Long id, @Valid @RequestBody ClassDTO classDTO) {
        EntityModel<ClassDTO> entityModel = cassembler
                .toModel(classMapper
                        .map(service
                                .editClassByClassIdAndCourseId(classMapper
                                        .map(classDTO, Class.class), id, current)
                                .get(), ClassDTO.class
                        ));
        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteClassByClassIdAndCourseId(@PathVariable Long current, @PathVariable Long id) {
        service.deleteClassByClassIdAndCourseId(id, current);
        return ResponseEntity
                .noContent()
                .build();
    }

    @PostMapping("/{id}/students")
    public ResponseEntity<Student> addStudent(@PathVariable Long id, @RequestBody Student student) {
        return new ResponseEntity<>(service.addStudentToClass(student, id).get(), HttpStatus.CREATED);
    }

    @GetMapping("/{id}/students")
    public ResponseEntity<List<Student>> getAllStudentsByClass(@PathVariable Long id){
        return new ResponseEntity<>(service.getAllStudentsByClass(id),HttpStatus.OK);
    }
}
