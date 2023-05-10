package edu.com.vegosbackend.service.course;

import edu.com.vegosbackend.domain.main.course.photo.Photo;
import edu.com.vegosbackend.repository.course.CourseRepo;
import edu.com.vegosbackend.repository.course.PhotoRepo;
import edu.com.vegosbackend.service.settings.exceptions.course.CourseNotFoundException;
import edu.com.vegosbackend.service.settings.exceptions.course.photo.*;
import edu.com.vegosbackend.service.settings.setters.Setter;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Data
public class PhotoService {
    private final PhotoRepo photoRepo;
    private final CourseRepo courseRepo;
    private final Setter<Photo> photoSetter;

    public Optional<Photo> addPhotoToCourseById(Photo photo, Long id) {
        photo
                .setCourse(courseRepo
                        .findById(id)
                        .orElseThrow(() -> new CourseNotFoundException(id)));
        return Optional.ofNullable(Optional.of(photoRepo
                        .save(photo))
                .orElseThrow(() -> new PhotoCannotBeAddedException(photo, id)));
    }

    public Optional<Photo> editPhotoByPhotoIdAndCourseId(Photo photo, Long id, Long current) {
        return Optional.of(Optional.of(photoRepo
                        .save(photoSetter
                                .setValue(courseRepo
                                                .findById(current)
                                                .orElseThrow(() -> new CourseNotFoundException(current))
                                                .getCourseDetails()
                                                .getPhotos()
                                                .stream()
                                                .filter(val -> val.getPhotoId() == id)
                                                .findFirst()
                                                .orElseThrow(() -> new PhotoNotFoundException(id)),
                                        photo)))
                .orElseThrow(() -> new PhotoCannotBeUpdatedException(photo, id, current)));
    }

    public void deletePhotoByPhotoIdAndCourseId(Long id, Long current) {
        photoRepo.deleteById(id);
        if (photoRepo.existsById(id)) {
            throw new PhotoCannotBeDeletedException(id, current);
        }
    }

    public Optional<Photo> getPhotoByPhotoIdAndCourseId(Long id, Long current) {
        return Optional.of(courseRepo
                .findById(current)
                .orElseThrow(() -> new CourseNotFoundException(current))
                .getCourseDetails()
                .getPhotos()
                .stream()
                .filter(val -> val.getPhotoId() == id)
                .findFirst()
                .orElseThrow(() -> new PhotoNotFoundException(id, current)));
    }

    public List<Photo> getAllPhotosByCourseId(Long id) {
        return photoRepo
                .findAllByCourse(courseRepo
                        .findById(id)
                        .orElseThrow(PhotosNotFoundException::new));
    }
}
