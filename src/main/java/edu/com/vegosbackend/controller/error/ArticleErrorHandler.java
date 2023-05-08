package edu.com.vegosbackend.controller.error;

import edu.com.vegosbackend.controller.ArticleController;
import edu.com.vegosbackend.controller.error.model.ExceptionBody;
import edu.com.vegosbackend.service.exceptions.article.*;
import edu.com.vegosbackend.service.exceptions.article.part.*;
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

@RestControllerAdvice(assignableTypes = ArticleController.class)
public class ArticleErrorHandler extends ResponseEntityExceptionHandler {

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
            PartsNotFoundException.class
    })
    public ResponseEntity<?> handleNotFoundException(RuntimeException e) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ExceptionBody.fillBody(e.getMessage(), HttpStatus.NOT_FOUND));
    }

    @ResponseBody
    @ExceptionHandler({
            ArticleCannotBeCreatedException.class,
            PartCannotBeAddedException.class
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
            PartCannotBeDeletedException.class
    })
    public ResponseEntity<?> handleNotModifiedException(RuntimeException e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ExceptionBody.fillBody(e.getMessage(), HttpStatus.BAD_REQUEST));
    }
}
