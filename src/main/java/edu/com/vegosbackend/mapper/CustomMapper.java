package edu.com.vegosbackend.mapper;

public interface CustomMapper<T, V> {
    T convertToEntity(V value);

    V convertToDTO(T value);
}
