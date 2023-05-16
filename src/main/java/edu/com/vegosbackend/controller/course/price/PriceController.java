package edu.com.vegosbackend.controller.course.price;

import edu.com.vegosbackend.controller.settings.model.assembler.course.price.PriceModelAssembler;
import edu.com.vegosbackend.controller.settings.model.dto.course.price.PriceDetailsDTO;
import edu.com.vegosbackend.domain.main.course.price.PriceDetails;
import edu.com.vegosbackend.service.course.price.PriceService;
import jakarta.validation.Valid;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("api/v1/courses/{current}/prices")
@Data
public class PriceController {
    private final PriceService priceService;
    private final ModelMapper priceMapper;
    private final PriceModelAssembler assembler;

    @GetMapping
    public CollectionModel<EntityModel<PriceDetailsDTO>> getAllPricesByCourse(@PathVariable Long current) {
        return CollectionModel.of(
                priceService.getAllPricesByCourseId(current)
                        .stream()
                        .map(val -> priceMapper.map(val, PriceDetailsDTO.class))
                        .map(assembler::toModel)
                        .collect(Collectors.toList()),
                linkTo(methodOn(PriceController.class)
                        .getAllPricesByCourse(current))
                        .withSelfRel());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<PriceDetailsDTO>> getPriceById(@PathVariable Long current, @PathVariable Long id) {
        EntityModel<PriceDetailsDTO> price = assembler
                .toModel(priceMapper
                        .map(priceService
                                .getPriceByPriceIdAndCourseId(current, id)
                                .get(), PriceDetailsDTO.class));
        return ResponseEntity
                .created(price.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(price);
    }

    @PostMapping
    public ResponseEntity<?> addPriceById(@PathVariable Long current, @Valid @RequestBody PriceDetailsDTO priceDetailsDTO) {
        EntityModel<PriceDetailsDTO> entityModel = assembler
                .toModel(priceMapper
                        .map(priceService
                                .addPriceToCourseById(priceMapper
                                        .map(priceDetailsDTO, PriceDetails.class), current)
                                .get(), PriceDetailsDTO.class));
        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editPriceById(@PathVariable Long current, @PathVariable Long id, @Valid @RequestBody PriceDetailsDTO priceDetailsDTO) {
        EntityModel<PriceDetailsDTO> entityModel = assembler
                .toModel(priceMapper
                        .map(priceService
                                .editPriceByPriceIdAndCourseId(priceMapper
                                        .map(priceDetailsDTO, PriceDetails.class), id, current)
                                .get(), PriceDetailsDTO.class));
        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePriceById(@PathVariable Long current, @PathVariable Long id) {
        priceService.deletePriceByPriceIdAndCourseId(id, current);
        return ResponseEntity
                .noContent()
                .build();
    }
}
