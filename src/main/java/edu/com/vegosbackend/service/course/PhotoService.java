package edu.com.vegosbackend.service.course;

import edu.com.vegosbackend.domain.main.course.Course;
import edu.com.vegosbackend.domain.main.course.photo.Photo;
import edu.com.vegosbackend.repository.course.CourseRepo;
import edu.com.vegosbackend.repository.course.PhotoRepo;
import edu.com.vegosbackend.service.settings.modifiers.GlobalClassGetter;
import edu.com.vegosbackend.service.settings.exceptions.BasicException;
import edu.com.vegosbackend.service.settings.exceptions.model.ExceptionModel;
import edu.com.vegosbackend.service.settings.exceptions.model.constants.MessageType;
import edu.com.vegosbackend.service.settings.exceptions.model.constants.ValueType;
import edu.com.vegosbackend.service.settings.modifiers.setters.Setter;
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
    private final GlobalClassGetter getter;

    public Optional<Photo> addPhotoToCourseById(Photo photo, Long id) {
        photo.setCourse(getter.getCourse(id));
        return Optional.ofNullable(Optional.of(photoRepo
                        .save(photo))
                .orElseThrow(() -> new BasicException(
                        Photo.class,
                        ValueType.ID,
                        MessageType.NOT_ADDED,
                        List.of(new ExceptionModel(Course.class, id.toString()))
                )));
    }

    public Optional<Photo> editPhotoByPhotoIdAndCourseId(Photo photo, Long id, Long current) {
        return Optional.of(Optional.of(photoRepo
                        .save(photoSetter
                                .setValue(getter.getPhoto(current, id), photo)))
                .orElseThrow(() -> new BasicException(
                        Photo.class,
                        ValueType.ID,
                        MessageType.NOT_UPDATED,
                        List.of(
                                new ExceptionModel(Photo.class, id.toString()),
                                new ExceptionModel(Course.class, current.toString())
                        )
                )));
    }

    public void deletePhotoByPhotoIdAndCourseId(Long id, Long current) {
        photoRepo.deleteById(id);
        if (photoRepo.existsById(id)) {
            throw new BasicException(
                    Photo.class,
                    ValueType.ID,
                    MessageType.NOT_DELETED,
                    List.of(
                            new ExceptionModel(Photo.class, id.toString()),
                            new ExceptionModel(Course.class, current.toString())
                    )
            );
        }
    }

    public Optional<Photo> getPhotoByPhotoIdAndCourseId(Long id, Long current) {
        return Optional.of(getter.getCourse(current)
                .getCourseDetails()
                .getPhotos()
                .stream()
                .filter(val -> val.getPhotoId() == id)
                .findFirst()
                .orElseThrow(() -> new BasicException(
                        Photo.class,
                        ValueType.ID,
                        MessageType.NOT_FOUND,
                        List.of(
                                new ExceptionModel(Photo.class, id.toString()),
                                new ExceptionModel(Course.class, current.toString())
                        )
                )));
    }

    public List<Photo> getAllPhotosByCourseId(Long id) {
        return photoRepo
                .findAllByCourse(courseRepo
                        .findById(id)
                        .orElseThrow(() -> new BasicException(
                                Photo.class,
                                ValueType.ID,
                                MessageType.NOT_FOUND,
                                List.of(new ExceptionModel(Course.class, id.toString()))
                        )));
    }
}
