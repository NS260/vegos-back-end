package edu.com.vegosbackend.service.settings.exceptions.model.builder;

import edu.com.vegosbackend.service.settings.exceptions.model.ExceptionModel;
import edu.com.vegosbackend.service.settings.exceptions.model.constants.MessageType;
import edu.com.vegosbackend.service.settings.exceptions.model.constants.ValueType;

import java.util.List;
import java.util.StringJoiner;

public class ExceptionMessageBuilder {

    public static String buildMessage(Class<?> aClass, ValueType valueType, MessageType message, List<ExceptionModel> list) {
        StringJoiner joiner = new StringJoiner(", ");
        list.forEach(val -> joiner.add(val.getObject().getSimpleName() + valueType.getValue() + val.getValue()));
        return aClass.getSimpleName() + message.getMessage() + joiner;
    }

    public static String buildMessage(Class<?> aClass, MessageType message) {
        return aClass.getSimpleName() + message;
    }
}
