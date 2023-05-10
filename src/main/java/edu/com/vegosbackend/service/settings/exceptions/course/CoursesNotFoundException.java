package edu.com.vegosbackend.service.settings.exceptions.course;

public class CoursesNotFoundException extends RuntimeException{
    public CoursesNotFoundException(){
        super("Courses not found!");
    }
}
