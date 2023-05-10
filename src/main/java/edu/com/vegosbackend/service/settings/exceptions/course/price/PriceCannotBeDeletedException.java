package edu.com.vegosbackend.service.settings.exceptions.course.price;

public class PriceCannotBeDeletedException extends RuntimeException {
    public PriceCannotBeDeletedException(Long id, Long current) {
        super("Price cannot be deleted. Price id: " + id + ", Course id: " + current);
    }
}
