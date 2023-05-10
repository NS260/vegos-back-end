package edu.com.vegosbackend.service.settings.exceptions.course.photo;

public class PhotoNotFoundException extends RuntimeException {
    public PhotoNotFoundException(Long id) {
        super("Photo not found. Photo id: " + id);
    }

    public PhotoNotFoundException(Long id, Long current) {
        super("Photo not found. Photo id: " + id + ", Course id: " + current);
    }
}
