package edu.com.vegosbackend.service.settings.exceptions.course.price;

import edu.com.vegosbackend.domain.main.course.price.PriceDetails;

public class PriceCannotBeAddedException extends RuntimeException {
    public PriceCannotBeAddedException(PriceDetails priceDetails, Long current) {
        super("Price cannot be added. Course id: " + current + ", Price: " + priceDetails);
    }
}
