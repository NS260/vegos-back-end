package edu.com.vegosbackend.service.settings.exceptions.course.photo;

import edu.com.vegosbackend.domain.main.course.photo.Photo;

public class PhotoCannotBeUpdatedException extends RuntimeException {
    public PhotoCannotBeUpdatedException(Photo photo, Long id, Long current) {
        super("Photo cannot be updated. Photo id: " + id + ", Course Id: " + current + ", Photo: " + photo);
    }
}
