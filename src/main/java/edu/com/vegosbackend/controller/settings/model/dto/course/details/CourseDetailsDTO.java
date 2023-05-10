package edu.com.vegosbackend.controller.settings.model.dto.course.details;

import edu.com.vegosbackend.controller.settings.model.dto.course.structure.StructureDTO;
import edu.com.vegosbackend.domain.constants.course.AgeGroup;
import edu.com.vegosbackend.domain.main.course.photo.Photo;
import lombok.Data;
import org.springframework.stereotype.Component;


import java.util.List;

@Component
@Data
public class CourseDetailsDTO {
    private String welcomeVideo;
    private AgeGroup ageGroup;
    private String description;
    private List<Photo> photos;
    private StructureDTO courseStructure;
}
