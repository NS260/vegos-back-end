package edu.com.vegosbackend.controller.settings.model.assembler.course.question;

import edu.com.vegosbackend.controller.course.question.QuestionController;
import edu.com.vegosbackend.controller.settings.model.dto.course.question.AnswerDTO;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class AnswerModelAssembler implements RepresentationModelAssembler<AnswerDTO, EntityModel<AnswerDTO>> {
    @Override
    public EntityModel<AnswerDTO> toModel(AnswerDTO entity) {
        return EntityModel.of(entity,
                linkTo(methodOn(QuestionController.class)
                        .getAnswerById(entity.getQuestion().getCourse().getId(), entity.getQuestion().getId(), entity.getId())).withSelfRel());
    }
}
