package exam.courseContext.infrastructure;

import exam.courseContext.domain.model.course.Course;
import exam.courseContext.domain.model.course.CourseId;
import exam.courseContext.domain.model.course.CourseRepository;
import exam.courseContext.domain.model.course.NotFoundCourseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@Slf4j
public class MemoryCourseRepository implements CourseRepository {
    private final Set<Course> courses = new HashSet<>();

    @Override
    public Course find(CourseId courseId) {
        return courses.stream()
                .filter(course -> course.getId().equals(courseId))
                .findFirst()
                .orElseThrow(() -> new NotFoundCourseException(courseId.getId()));
    }

    @Override
    public Course save(Course course) {
        try {
            final Course needUpdated = find(course.getId());
            needUpdated.update(course.getVideoLink(), course.getExamination());
            courses.add(needUpdated);
            return needUpdated;
        } catch (NotFoundCourseException e) {
            log.info("Not found course, should insert new one");
            courses.add(course);
            return course;
        }
    }

    @Override
    public List<Course> getAll() {
        return Collections.unmodifiableList(new ArrayList<>(courses));
    }
}
