package edu.com.vegosbackend.controller.course;

import edu.com.vegosbackend.controller.settings.model.assembler.course.PhotoModelAssembler;
import edu.com.vegosbackend.controller.settings.model.dto.course.photo.PhotoDTO;
import edu.com.vegosbackend.mapper.course.PhotoMapper;
import edu.com.vegosbackend.service.course.PhotoService;
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
@RequestMapping("api/v1/courses/{current}/photos")
@Data
@AllArgsConstructor
public class PhotoController {
    private final PhotoService photoService;
    private final PhotoMapper photoMapper;
    private final PhotoModelAssembler passambler;

    @GetMapping
    public CollectionModel<EntityModel<PhotoDTO>> getAllPhotosByCourseId(@PathVariable Long current) {
        return CollectionModel.of(photoService
                        .getAllPhotosByCourseId(current)
                        .stream()
                        .map(photoMapper::convertToDTO)
                        .map(passambler::toModel)
                        .collect(Collectors.toList()),
                linkTo(methodOn(PhotoController.class)
                        .getAllPhotosByCourseId(current))
                        .withSelfRel());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<PhotoDTO>> getPhotoByPhotoIdAndCourseId(@PathVariable Long current, @PathVariable Long id) {
        EntityModel<PhotoDTO> photo = passambler
                .toModel(photoMapper
                        .convertToDTO(photoService
                                .getPhotoByPhotoIdAndCourseId(id, current).get()));
        return ResponseEntity
                .created(photo.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(photo);
    }

    @PostMapping
    public ResponseEntity<?> addPhotoToCourseById(@PathVariable Long current,@Valid @RequestBody PhotoDTO photo) {
        EntityModel<PhotoDTO> entityModel = passambler
                .toModel(photoMapper
                        .convertToDTO(photoService
                                .addPhotoToCourseById(photoMapper
                                        .convertToEntity(photo), current)
                                .get()));
        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editPhotoByPhotoIdAndCourseId(@PathVariable Long current, @PathVariable Long id,@Valid @RequestBody PhotoDTO photoDTO) {
        EntityModel<PhotoDTO> entityModel = passambler
                .toModel(photoMapper
                        .convertToDTO(photoService
                                .editPhotoByPhotoIdAndCourseId(photoMapper
                                        .convertToEntity(photoDTO), id, current)
                                .get()
                        ));
        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePhotoByPhotoIdAndCourseId(@PathVariable Long current, @PathVariable Long id) {
        photoService.deletePhotoByPhotoIdAndCourseId(id, current);
        return ResponseEntity
                .noContent()
                .build();
    }
}
