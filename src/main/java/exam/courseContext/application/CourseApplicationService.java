package exam.courseContext.application;

import exam.courseContext.domain.model.course.Course;
import exam.courseContext.domain.model.course.CourseFactory;
import exam.courseContext.domain.model.course.CourseId;
import exam.courseContext.domain.model.course.CourseRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CourseApplicationService {
    private final CourseRepository courseRepository;

    @Autowired
    public CourseApplicationService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public Course createCourse(CreateCourseCommand createCourseCommand) {
        final Course course =
                CourseFactory.create(
                        createCourseCommand.getVideoLink(),
                        createCourseCommand.getExamination().getId(),
                        createCourseCommand.getExamination().getName(),
                        createCourseCommand.getExamination().getDescription());

        return courseRepository.save(course);
    }

    public Course updateCourse(String id, CreateCourseCommand createCourseCommand) {
        final Course course =
                Course.create(
                        new CourseId(id),
                        createCourseCommand.getVideoLink(),
                        new Course.Examination(
                                createCourseCommand.getExamination().getId(),
                                createCourseCommand.getExamination().getName(),
                                createCourseCommand.getExamination().getDescription()));

        return courseRepository.save(course);
    }

    public void publish(String courseId) {
        final Course course = courseRepository.find(new CourseId(courseId));
        course.publish();
        courseRepository.save(course);
    }
}
