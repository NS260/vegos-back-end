package edu.com.vegosbackend.service.course;

import edu.com.vegosbackend.domain.main.course.price.PriceDetails;
import edu.com.vegosbackend.repository.course.CourseRepo;
import edu.com.vegosbackend.repository.course.PriceRepo;
import edu.com.vegosbackend.service.settings.exceptions.course.CourseNotFoundException;
import edu.com.vegosbackend.service.settings.exceptions.course.price.*;
import edu.com.vegosbackend.service.settings.setters.Setter;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Data
public class PriceService {
    private final PriceRepo priceRepo;
    private final Setter<PriceDetails> priceSetter;
    private final CourseRepo courseRepo;

    public List<PriceDetails> getAllPricesByCourseId(Long current) {
        return priceRepo
                .findAllByCourse(courseRepo
                        .findById(current)
                        .orElseThrow(() -> new PricesNotFoundException(current)));
    }

    public Optional<PriceDetails> getPriceByPriceIdAndCourseId(Long current, Long id) {
        return Optional.of(courseRepo
                .findById(current)
                .orElseThrow(() -> new CourseNotFoundException(current))
                .getPriceDetails()
                .stream()
                .filter(val -> val.getPriceId() == id)
                .findFirst()
                .orElseThrow(() -> new PriceNotFoundException(id, current)));
    }

    public Optional<PriceDetails> addPriceToCourseById(PriceDetails priceDetails, Long current) {
        priceDetails
                .setCourse(courseRepo
                        .findById(current)
                        .orElseThrow(() -> new CourseNotFoundException(current)));
        return Optional.ofNullable(Optional.of(priceRepo
                        .save(priceDetails))
                .orElseThrow(() -> new PriceCannotBeAddedException(priceDetails, current)));
    }

    public Optional<PriceDetails> editPriceByPriceIdAndCourseId(PriceDetails priceDetails, Long id, Long current) {
        return Optional.of(Optional.of(priceRepo
                        .save(priceSetter
                                .setValue(courseRepo
                                                .findById(current)
                                                .orElseThrow(() -> new CourseNotFoundException(current))
                                                .getPriceDetails()
                                                .stream()
                                                .filter(val -> val.getPriceId() == id)
                                                .findFirst()
                                                .orElseThrow(() -> new PriceNotFoundException(id, current)),
                                        priceDetails)))
                .orElseThrow(() -> new PriceCannotBeUpdatedException(priceDetails, id, current)));
    }

    public void deletePriceByPriceIdAndCourseId(Long id, Long current) {
        priceRepo.deleteById(id);
        if (courseRepo
                .findById(current)
                .orElseThrow(() -> new CourseNotFoundException(current))
                .getPriceDetails()
                .stream().anyMatch(val -> val.getPriceId() == id)) {
            throw new PriceCannotBeDeletedException(id, current);
        }
    }
}
