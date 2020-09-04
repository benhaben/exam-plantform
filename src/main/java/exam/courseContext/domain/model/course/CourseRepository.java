package exam.courseContext.domain.model.course;

import java.util.List;

public interface CourseRepository {
    Course find(CourseId id);

    Course save(Course course);

    List<Course> getAll();
}
