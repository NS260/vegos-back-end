package edu.com.vegosbackend.service.settings.setters.course;

import edu.com.vegosbackend.domain.main.course.price.PriceDetails;
import edu.com.vegosbackend.service.settings.setters.Setter;
import org.springframework.stereotype.Component;

@Component
public class PriceSetter implements Setter<PriceDetails> {
    @Override
    public PriceDetails setValue(PriceDetails before, PriceDetails after) {
        after.setPriceId(before.getPriceId());
        after.setClassType(before.getClassType());
        after.setCourse(before.getCourse());
        if (after.getPrice() <= 0) {
            after.setPrice(before.getPrice());
        }
        return after;
    }
}
