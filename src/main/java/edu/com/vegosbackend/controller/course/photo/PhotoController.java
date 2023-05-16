package edu.com.vegosbackend.controller.course.photo;

import edu.com.vegosbackend.controller.settings.model.assembler.course.photo.PhotoModelAssembler;
import edu.com.vegosbackend.controller.settings.model.dto.course.photo.PhotoDTO;
import edu.com.vegosbackend.domain.main.course.photo.Photo;
import edu.com.vegosbackend.service.course.photo.PhotoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
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
@RequestMapping("api/v1/courses/{current}/photos")
@Data
@AllArgsConstructor
public class PhotoController {
    private final PhotoService photoService;
    private final ModelMapper photoMapper;
    private final PhotoModelAssembler passambler;

    @GetMapping
    public CollectionModel<EntityModel<PhotoDTO>> getAllPhotosByCourseId(@PathVariable Long current) {
        return CollectionModel.of(photoService
                        .getAllPhotosByCourseId(current)
                        .stream()
                        .map(val -> photoMapper.map(val, PhotoDTO.class))
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
                        .map(photoService
                                .getPhotoByPhotoIdAndCourseId(id, current)
                                .get(), PhotoDTO.class));
        return ResponseEntity
                .created(photo.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(photo);
    }

    @PostMapping
    public ResponseEntity<?> addPhotoToCourseById(@PathVariable Long current, @Valid @RequestBody PhotoDTO photo) {
        EntityModel<PhotoDTO> entityModel = passambler
                .toModel(photoMapper
                        .map(photoService
                                .addPhotoToCourseById(photoMapper
                                        .map(photo, Photo.class), current)
                                .get(), PhotoDTO.class));
        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editPhotoByPhotoIdAndCourseId(@PathVariable Long current, @PathVariable Long id, @Valid @RequestBody PhotoDTO photoDTO) {
        EntityModel<PhotoDTO> entityModel = passambler
                .toModel(photoMapper
                        .map(photoService
                                .editPhotoByPhotoIdAndCourseId(photoMapper
                                        .map(photoDTO, Photo.class), id, current)
                                .get(), PhotoDTO.class
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
