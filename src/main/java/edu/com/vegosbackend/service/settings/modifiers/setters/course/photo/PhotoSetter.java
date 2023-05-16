package edu.com.vegosbackend.service.settings.modifiers.setters.course.photo;

import edu.com.vegosbackend.domain.main.course.photo.Photo;
import edu.com.vegosbackend.service.settings.modifiers.setters.Setter;
import org.springframework.stereotype.Component;

@Component
public class PhotoSetter implements Setter<Photo> {
    @Override
    public Photo setValue(Photo before, Photo after) {
        after.setPhotoId(before.getPhotoId());
        after.setCourse(before.getCourse());
        if (after.getPhotoUrl() == null) {
            after.setPhotoUrl(before.getPhotoUrl());
        }
        return after;
    }
}
