package edu.com.vegosbackend.service.settings.exceptions;

import edu.com.vegosbackend.service.settings.exceptions.model.ExceptionModel;
import edu.com.vegosbackend.service.settings.exceptions.model.builder.ExceptionMessageBuilder;
import edu.com.vegosbackend.service.settings.exceptions.model.constants.MessageType;
import edu.com.vegosbackend.service.settings.exceptions.model.constants.ValueType;
import lombok.Getter;

import java.util.List;

@Getter
public class BasicException extends RuntimeException {
    private final MessageType messageType;

    public BasicException(Class<?> aClass, ValueType valueType, MessageType message, List<ExceptionModel> list) {
        super(ExceptionMessageBuilder.buildMessage(aClass, valueType, message, list));
        this.messageType = message;
    }

    public BasicException(Class<?> aClass, MessageType message) {
        super(ExceptionMessageBuilder.buildMessage(aClass, message));
        this.messageType = message;
    }
}
