package edu.com.vegosbackend.service.settings.exceptions.course.price;

public class PriceNotFoundException extends RuntimeException {
    public PriceNotFoundException(Long id, Long current) {
        super("Price not found. Price id: " + id + ", Course id: " + current);
    }
}
