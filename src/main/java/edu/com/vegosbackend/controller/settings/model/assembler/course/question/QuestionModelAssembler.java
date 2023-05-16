package edu.com.vegosbackend.controller.settings.model.assembler.course.question;

import edu.com.vegosbackend.controller.course.question.QuestionController;
import edu.com.vegosbackend.controller.settings.model.dto.course.question.QuestionDTO;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class QuestionModelAssembler implements RepresentationModelAssembler<QuestionDTO, EntityModel<QuestionDTO>> {
    @Override
    public EntityModel<QuestionDTO> toModel(QuestionDTO entity) {
        return EntityModel.of(entity,
                linkTo(methodOn(QuestionController.class).getQuestionById(entity.getCourse().getId(), entity.getId())).withSelfRel(),
                linkTo(methodOn(QuestionController.class).getAllQuestionByCourse(entity.getCourse().getId())).withRel("questions"));
    }
}
