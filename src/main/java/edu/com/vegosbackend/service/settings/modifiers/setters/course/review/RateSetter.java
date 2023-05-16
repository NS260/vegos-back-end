package edu.com.vegosbackend.service.settings.modifiers.setters.course.review;

import edu.com.vegosbackend.domain.main.course.review.Rate;
import edu.com.vegosbackend.service.settings.modifiers.setters.Setter;
import org.springframework.stereotype.Component;

@Component
public class RateSetter implements Setter<Rate> {
    @Override
    public Rate setValue(Rate before, Rate after) {
        after.setReview(before.getReview());
        after.setId(before.getId());
        if (after.getValue() <= 0) {
            after.setValue(before.getValue());
        }
        if (after.getType() == null) {
            after.setType(before.getType());
        }
        return after;
    }
}
