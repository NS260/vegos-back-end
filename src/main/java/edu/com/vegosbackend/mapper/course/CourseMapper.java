package edu.com.vegosbackend.mapper.course;

import edu.com.vegosbackend.controller.settings.model.dto.course.CourseDTO;
import edu.com.vegosbackend.domain.main.course.Course;
import edu.com.vegosbackend.mapper.CustomMapper;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@Data
public class CourseMapper implements CustomMapper<Course, CourseDTO> {
    private final ModelMapper mapper;

    @Override
    public Course convertToEntity(CourseDTO value) {
        return mapper.map(value, Course.class);
    }

    @Override
    public CourseDTO convertToDTO(Course value) {
        return mapper.map(value, CourseDTO.class);
    }
}
