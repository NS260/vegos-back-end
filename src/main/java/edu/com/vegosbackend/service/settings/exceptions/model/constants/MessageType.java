package edu.com.vegosbackend.service.settings.exceptions.model.constants;

import lombok.Getter;

@Getter
public enum MessageType {
    NOT_FOUND(" not found. "),
    NOT_ADDED(" not added. "),
    NOT_CREATED(" not created. "),
    NOT_DELETED(" not deleted. "),
    NOT_UPDATED(" not updated. ");

    private final String message;

    MessageType(String message) {
        this.message = message;
    }
}
