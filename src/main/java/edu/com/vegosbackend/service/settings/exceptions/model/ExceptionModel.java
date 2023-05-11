package edu.com.vegosbackend.service.settings.exceptions.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ExceptionModel {
    private Class<?> object;
    private String value;

    public ExceptionModel(Class<?> object) {
        this.object = object;
    }
}
