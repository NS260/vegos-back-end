package edu.com.vegosbackend.service.course.price;

import edu.com.vegosbackend.domain.main.course.Course;
import edu.com.vegosbackend.domain.main.course.price.PriceDetails;
import edu.com.vegosbackend.repository.course.price.PriceRepo;
import edu.com.vegosbackend.service.settings.modifiers.GlobalClassGetter;
import edu.com.vegosbackend.service.settings.exceptions.BasicException;
import edu.com.vegosbackend.service.settings.exceptions.model.ExceptionModel;
import edu.com.vegosbackend.service.settings.exceptions.model.constants.MessageType;
import edu.com.vegosbackend.service.settings.exceptions.model.constants.ValueType;
import edu.com.vegosbackend.service.settings.modifiers.setters.Setter;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Data
public class PriceService {
    private final PriceRepo priceRepo;
    private final Setter<PriceDetails> priceSetter;
    private final GlobalClassGetter getter;

    public List<PriceDetails> getAllPricesByCourseId(Long current) {
        return priceRepo
                .findAllByCourse(Optional.of(getter.getCourse(current))
                        .orElseThrow(() -> new BasicException(
                                PriceDetails.class,
                                ValueType.ID,
                                MessageType.NOT_FOUND,
                                List.of(new ExceptionModel(Course.class, current.toString())))));
    }

    public Optional<PriceDetails> getPriceByPriceIdAndCourseId(Long current, Long id) {
        return Optional.of(Optional.of(getter.getPrice(current, id))
                .orElseThrow(() -> new BasicException(
                        PriceDetails.class,
                        ValueType.ID,
                        MessageType.NOT_FOUND,
                        List.of(
                                new ExceptionModel(PriceDetails.class, id.toString()),
                                new ExceptionModel(Course.class, current.toString())))));
    }

    public Optional<PriceDetails> addPriceToCourseById(PriceDetails priceDetails, Long current) {
        priceDetails.setCourse(getter.getCourse(current));
        return Optional.ofNullable(Optional.of(priceRepo
                        .save(priceDetails))
                .orElseThrow(() -> new BasicException(
                        priceDetails.getClass(),
                        ValueType.ID,
                        MessageType.NOT_ADDED,
                        List.of(new ExceptionModel(priceDetails.getCourse().getClass())))));
    }

    public Optional<PriceDetails> editPriceByPriceIdAndCourseId(PriceDetails priceDetails, Long id, Long current) {
        return Optional.of(Optional.of(priceRepo
                        .save(priceSetter.setValue(getter.getPrice(current, id), priceDetails)))
                .orElseThrow(() -> new BasicException(
                        PriceDetails.class,
                        ValueType.ID,
                        MessageType.NOT_UPDATED,
                        List.of(
                                new ExceptionModel(PriceDetails.class, id.toString()),
                                new ExceptionModel(Course.class, current.toString())))));
    }

    public void deletePriceByPriceIdAndCourseId(Long id, Long current) {
        priceRepo.deleteById(id);
        if (getter.getCourse(current)
                .getPriceDetails()
                .stream()
                .anyMatch(val -> val.getPriceId() == id)) {
            throw new BasicException(
                    PriceDetails.class,
                    ValueType.ID,
                    MessageType.NOT_DELETED,
                    List.of(
                            new ExceptionModel(PriceDetails.class, id.toString()),
                            new ExceptionModel(Course.class, current.toString())));
        }
    }
}
