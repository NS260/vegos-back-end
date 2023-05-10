package edu.com.vegosbackend.service.settings.exceptions.course.price;

public class PricesNotFoundException extends RuntimeException {
    public PricesNotFoundException(Long current) {
        super("Prices not found. Course id: " + current);
    }
}
