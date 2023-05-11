package edu.com.vegosbackend.service.settings.exceptions.model.constants;

import lombok.Getter;

@Getter
public enum ValueType {
    ID(" id: "), NAME(" name: "), EMAIL(" email: ");

    private final String value;

    ValueType(String value) {
        this.value = value;
    }
}
