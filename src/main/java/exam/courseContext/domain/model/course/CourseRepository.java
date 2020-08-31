package exam.courseContext.domain.model.course;

import java.util.List;

public interface CourseRepository {
    Course find(CourseId id);

    void save(Course course);

    List<Course> getAll();
}
