package edu.com.vegosbackend.service.settings.modifiers.setters.course.review;

import edu.com.vegosbackend.domain.main.course.review.Review;
import edu.com.vegosbackend.service.settings.modifiers.setters.Setter;
import org.springframework.stereotype.Component;

@Component
public class ReviewSetter implements Setter<Review> {
    @Override
    public Review setValue(Review before, Review after) {
        after.setCourse(before.getCourse());
        after.setId(before.getId());
        after.setStudent(before.getStudent());
        if (after.getText() == null) {
            after.setText(before.getText());
        }
        if (after.getRates() == null) {
            after.setRates(before.getRates());
        }
        return after;
    }
}
