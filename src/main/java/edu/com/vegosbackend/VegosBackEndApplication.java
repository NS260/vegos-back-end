package edu.com.vegosbackend;

import edu.com.vegosbackend.model.addons.settings.CourseFeatures;
import edu.com.vegosbackend.model.addons.structure.CourseStructure;
import edu.com.vegosbackend.model.constants.AgeGroup;
import edu.com.vegosbackend.model.main.course.Course;
import edu.com.vegosbackend.model.constants.Category;
import edu.com.vegosbackend.model.main.course.CourseDetails;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;

@SpringBootApplication
public class VegosBackEndApplication {

    public static void main(String[] args) {
//        SpringApplication.run(VegosBackEndApplication.class, args);

        CourseFeatures courseFeatures = new CourseFeatures(
                true,
                true,
                false,
                true,
                false);
        CourseDetails courseDetails = new CourseDetails(
                "video",
                AgeGroup.ADULT,
                "desc",
                new HashSet<>(),
                new HashSet<>(),
                new HashSet<>(),
                new HashSet<>(),
                new CourseStructure());
        Course course = new Course(
                1,
                "Name",
                courseDetails,
                courseFeatures,
                "",
                Category.Business,
                Collections.emptySet(),
                "short desc",
                "3 months",
                4.44,
                "image",
                LocalDateTime.now(),
                "UA"
                );

        System.out.println(course);
    }
}
