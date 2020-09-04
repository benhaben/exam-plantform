package exam.courseContext.userInterface;

import exam.courseContext.domain.model.course.Course;
import exam.courseContext.domain.model.course.CourseStatus;
import lombok.Value;

import java.time.LocalDateTime;

@Value
public class CourseDTO {
    String id;
    LocalDateTime createTime;
    String videoLink;
    CourseStatus status;
    LocalDateTime publishTime;
    Examination examination;

    public static CourseDTO from(Course course) {
        return new CourseDTO(
                course.getId().getId(),
                course.getCreateTime(),
                course.getVideoLink(),
                course.getCourseStatus(),
                course.getPublishTime(),
                new Examination(
                        course.getExamination().getId(),
                        course.getExamination().getName(),
                        course.getExamination().getDescription()));
    }

    @Value
    public static class Examination {
        String id;
        String name;
        String description;
    }
}
