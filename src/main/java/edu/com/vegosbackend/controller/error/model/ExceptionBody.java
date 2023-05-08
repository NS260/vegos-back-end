package edu.com.vegosbackend.controller.error.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ExceptionBody {
    private LocalDateTime errorDateTime;
    private HttpStatus status;
    private String message;

    public static ExceptionBody fillBody(String message, HttpStatus status) {
        return new ExceptionBody(LocalDateTime.now(), status, message);
    }
}
