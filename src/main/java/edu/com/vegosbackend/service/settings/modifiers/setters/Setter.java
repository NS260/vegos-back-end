package edu.com.vegosbackend.service.settings.modifiers.setters;

public interface Setter<A> {
    A setValue(A before, A after);
}
