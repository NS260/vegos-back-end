package edu.com.vegosbackend.service.settings.exceptions.course.photo;

public class PhotosNotFoundException extends RuntimeException{
    public PhotosNotFoundException(){
        super("Photos not found!");
    }
}
