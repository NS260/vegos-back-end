package edu.com.vegosbackend.service.settings.exceptions.course.photo;

import edu.com.vegosbackend.domain.main.course.photo.Photo;

public class PhotoCannotBeAddedException extends RuntimeException {
    public PhotoCannotBeAddedException(Photo photo, Long id) {
        super("Photo cannot be added. Course id: " + id + ", Photo: " + photo);
    }
}
