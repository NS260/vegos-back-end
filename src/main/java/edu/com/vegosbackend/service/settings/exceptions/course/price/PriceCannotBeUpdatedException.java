package edu.com.vegosbackend.service.settings.exceptions.course.price;

import edu.com.vegosbackend.domain.main.course.price.PriceDetails;

public class PriceCannotBeUpdatedException extends RuntimeException {
    public PriceCannotBeUpdatedException(PriceDetails priceDetails, Long id, Long current) {
        super("Price cannot be updated. Price id: " + id + ", Course id: " + current + ", Price: " + priceDetails);
    }
}
