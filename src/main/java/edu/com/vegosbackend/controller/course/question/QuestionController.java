package edu.com.vegosbackend.controller.course.question;

import edu.com.vegosbackend.controller.settings.model.assembler.course.question.AnswerModelAssembler;
import edu.com.vegosbackend.controller.settings.model.assembler.course.question.QuestionModelAssembler;
import edu.com.vegosbackend.controller.settings.model.dto.course.question.AnswerDTO;
import edu.com.vegosbackend.controller.settings.model.dto.course.question.QuestionDTO;
import edu.com.vegosbackend.domain.main.course.question.Answer;
import edu.com.vegosbackend.domain.main.course.question.Question;
import edu.com.vegosbackend.service.course.question.QuestionService;
import jakarta.validation.Valid;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("api/v1/courses/{current}/questions")
@Data
public class QuestionController {
    private final QuestionService service;
    private final ModelMapper mapper;
    private final AnswerModelAssembler assembler;
    private final QuestionModelAssembler qassembler;

    @GetMapping
    public CollectionModel<EntityModel<QuestionDTO>> getAllQuestionByCourse(@PathVariable Long current) {
        return CollectionModel.of(
                service.getAllQuestionsByCourseId(current)
                        .stream()
                        .map(val -> mapper.map(val, QuestionDTO.class))
                        .map(qassembler::toModel)
                        .collect(Collectors.toList()),
                linkTo(methodOn(QuestionController.class)
                        .getAllQuestionByCourse(current))
                        .withSelfRel());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<QuestionDTO>> getQuestionById(@PathVariable Long current, @PathVariable Long id) {
        EntityModel<QuestionDTO> entityModel = qassembler
                .toModel(mapper
                        .map(service
                                .getQuestionByQuestionIdAndCourseId(current, id)
                                .get(), QuestionDTO.class));
        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @PostMapping
    public ResponseEntity<?> addQuestionById(@PathVariable Long current, @Valid @RequestBody QuestionDTO questionDTO) {
        questionDTO.setAskedDate(LocalDateTime.now());
        EntityModel<QuestionDTO> entityModel = qassembler
                .toModel(mapper
                        .map(service
                                .addQuestionToCourseById(mapper
                                        .map(questionDTO, Question.class), current)
                                .get(), QuestionDTO.class));
        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editQuestionById(@PathVariable Long current, @PathVariable Long id, @RequestBody QuestionDTO questionDTO) {
        EntityModel<QuestionDTO> entityModel = qassembler
                .toModel(mapper
                        .map(service
                                .editQuestionByQuestionIdAndCourseId(mapper
                                        .map(questionDTO, Question.class), id, current)
                                .get(), QuestionDTO.class));
        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteQuestionById(@PathVariable Long current, @PathVariable Long id) {
        service.deleteQuestionByQuestionIdAndCourseId(id, current);
        return ResponseEntity
                .noContent()
                .build();
    }

    @GetMapping("/{question}/answers/{id}")
    public ResponseEntity<EntityModel<AnswerDTO>> getAnswerById(@PathVariable Long current, @PathVariable Long question, @PathVariable Long id) {
        EntityModel<AnswerDTO> entityModel = assembler
                .toModel(mapper
                        .map(service
                                .getAnswerByAnswerIdAndQuestionIdAndCourseId(current, question, id)
                                .get(), AnswerDTO.class));
        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @PostMapping("/{question}/answers")
    public ResponseEntity<?> addAnswerById(@PathVariable Long current, @PathVariable Long question, @Valid @RequestBody AnswerDTO answerDTO) {
        EntityModel<AnswerDTO> entityModel = assembler
                .toModel(mapper
                        .map(service
                                .addAnswerToCourseByQuestionIdAndCourseId(current, question, mapper
                                        .map(setStartValues(answerDTO, current, question), Answer.class))
                                .get(), AnswerDTO.class));
        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @PutMapping("/{question}/answers/{id}")
    public ResponseEntity<?> editAnswerById(@PathVariable Long current, @PathVariable Long question, @PathVariable Long id, @Valid @RequestBody AnswerDTO answerDTO) {
        EntityModel<AnswerDTO> entityModel = assembler
                .toModel(mapper
                        .map(service
                                .editAnswerByAnswerIdAndQuestionIdAndCourseId(current, question, id, mapper
                                        .map(answerDTO, Answer.class))
                                .get(), AnswerDTO.class));
        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @DeleteMapping("/{question}/answers/{id}")
    public ResponseEntity<?> deleteAnswerById(@PathVariable Long current, @PathVariable Long question, @PathVariable Long id) {
        service.deleteAnswerByAnswerIdAndQuestionIdAndCourseId(current, question, id);
        return ResponseEntity
                .noContent()
                .build();
    }

    private AnswerDTO setStartValues(AnswerDTO answerDTO, Long current, Long question) {
        answerDTO.setAnsweredDate(LocalDateTime.now());
        answerDTO.setQuestion(service.getGetter().getQuestion(current, question));
        return answerDTO;
    }
}
