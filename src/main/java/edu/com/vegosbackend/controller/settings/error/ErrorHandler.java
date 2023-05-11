package edu.com.vegosbackend.controller.settings.error;

import edu.com.vegosbackend.controller.settings.error.model.ExceptionBody;
import edu.com.vegosbackend.service.settings.exceptions.BasicException;
import edu.com.vegosbackend.service.settings.exceptions.model.constants.MessageType;
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

@RestControllerAdvice
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
            BasicException.class
    })
    public ResponseEntity<?> handleException(BasicException e) {
        if (e.getMessageType().equals(MessageType.NOT_FOUND)) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(ExceptionBody.fillBody(e.getMessage(), HttpStatus.NOT_FOUND));
        }
        if (e.getMessageType().equals(MessageType.NOT_ADDED) || e.getMessageType().equals(MessageType.NOT_CREATED)) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ExceptionBody.fillBody(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR));
        }
        if (e.getMessageType().equals(MessageType.NOT_UPDATED) || e.getMessageType().equals(MessageType.NOT_DELETED)) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(ExceptionBody.fillBody(e.getMessage(), HttpStatus.BAD_REQUEST));
        }
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED)
                .body(e.getMessage());
    }
}
