package edu.com.vegosbackend.service.settings.setters;

public interface Setter<A> {
    A setValue(A before, A after);
}
