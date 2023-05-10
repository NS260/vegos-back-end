package edu.com.vegosbackend.controller.settings.error;

import edu.com.vegosbackend.controller.article.ArticleController;
import edu.com.vegosbackend.controller.article.PartController;
import edu.com.vegosbackend.controller.course.CourseController;
import edu.com.vegosbackend.controller.course.PhotoController;
import edu.com.vegosbackend.controller.course.PriceController;
import edu.com.vegosbackend.controller.course.StructureController;
import edu.com.vegosbackend.controller.settings.error.model.ExceptionBody;
import edu.com.vegosbackend.service.settings.exceptions.article.*;
import edu.com.vegosbackend.service.settings.exceptions.article.part.*;
import edu.com.vegosbackend.service.settings.exceptions.course.*;
import edu.com.vegosbackend.service.settings.exceptions.course.photo.*;
import edu.com.vegosbackend.service.settings.exceptions.course.price.*;
import edu.com.vegosbackend.service.settings.exceptions.course.structure.*;
import edu.com.vegosbackend.service.settings.exceptions.course.structure.subTheme.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice(assignableTypes =
        {
                ArticleController.class,
                PartController.class,
                CourseController.class,
                PhotoController.class,
                PriceController.class,
                StructureController.class
        })
public class ErrorHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String field = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(field, message);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ResponseBody
    @ExceptionHandler({
            ArticleNotFoundException.class,
            PartNotFoundException.class,
            ArticlesNotFoundException.class,
            PartsNotFoundException.class,
            PhotoNotFoundException.class,
            PhotosNotFoundException.class,
            CourseNotFoundException.class,
            CoursesNotFoundException.class,
            PriceNotFoundException.class,
            PricesNotFoundException.class,
            ThemesNotFoundException.class,
            ThemeNotFoundException.class,
            SubThemeNotFoundException.class,
            SubThemesNotFoundException.class
    })
    public ResponseEntity<?> handleNotFoundException(RuntimeException e) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ExceptionBody.fillBody(e.getMessage(), HttpStatus.NOT_FOUND));
    }

    @ResponseBody
    @ExceptionHandler({
            ArticleCannotBeCreatedException.class,
            PartCannotBeAddedException.class,
            PhotoCannotBeAddedException.class,
            CourseCannotBeCreatedException.class,
            PriceCannotBeAddedException.class,
            ThemeCannotBeAddedException.class,
            SubThemeCannotBeAddedException.class
    })
    public ResponseEntity<?> handleNotCreatedException(RuntimeException e) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ExceptionBody.fillBody(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR));
    }

    @ResponseBody
    @ExceptionHandler({
            ArticleCannotBeUpdatedException.class,
            PartCannotBeUpdatedException.class,
            ArticleCannotBeDeletedException.class,
            PartCannotBeDeletedException.class,
            PhotoCannotBeDeletedException.class,
            PhotoCannotBeUpdatedException.class,
            CourseCannotBeDeletedException.class,
            CourseCannotBeUpdatedException.class,
            PriceCannotBeDeletedException.class,
            PriceCannotBeUpdatedException.class,
            ThemeCannotBeDeletedException.class,
            ThemeCannotBeUpdatedException.class,
            SubThemeCannotBeDeletedException.class,
            SubThemeCannotBeUpdatedException.class
    })
    public ResponseEntity<?> handleNotModifiedException(RuntimeException e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ExceptionBody.fillBody(e.getMessage(), HttpStatus.BAD_REQUEST));
    }
}
