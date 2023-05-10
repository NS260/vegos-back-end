package edu.com.vegosbackend.service.settings.exceptions.course.photo;

public class PhotoCannotBeDeletedException extends RuntimeException {
    public PhotoCannotBeDeletedException(Long id, Long current) {
        super("Photo cannot be deleted. Photo id: " + id + ", Course id: " + current);
    }
}
