package edu.com.vegosbackend.mapper;

public interface CustomMapper<Entity, DTO> {
    Entity convertToEntity(DTO value);

    DTO convertToDTO(Entity value);
}
