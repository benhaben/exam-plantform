package exam.courseContext.infrastructure;

import exam.courseContext.domain.model.course.Course;
import exam.courseContext.domain.model.course.CourseId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class MemoryCourseRepositoryTest {
    private MemoryCourseRepository repository;

    @BeforeEach
    void setUp() {
        repository = new MemoryCourseRepository();
    }

    @Test
    void should_create_new_course_when_repository_is_empty() {
        final Course course =
                newCourse("course-id-1", "https://vlink", "exam-id", "exam-name", "exam-desc");

        final Course inserted = repository.save(course);

        int count = repository.getAll().size();
        assertEquals(count, 1);
        assertEquals(inserted.getExamination().getId(), "exam-id");
        assertEquals(inserted.getVideoLink(), "https://vlink");
        assertEquals(inserted.getId().getId(), "course-id-1");
    }

    @Test
    void should_find_course_by_id_when_repository_has_same_id() {
        final List<Course> courses = mockCoursesByCount(3);


        final List<Course> inserted =
                courses.stream()
                        .map(course -> repository.save(course))
                        .collect(Collectors.toList());
        assertEquals(courses.size(), inserted.size());

        final Course course = repository.find(new CourseId("course-id-1"));
        assertNotNull(course);
        assertEquals(course.getId().getId(), "course-id-1");
        assertEquals(course.getVideoLink(), "https://vlink-1");
        assertEquals(course.getExamination().getId(), "exam-id-1");
    }

    @Test
    void should_update_course_correctly_when_repository_has_same_id() {
        final List<Course> courses = mockCoursesByCount(10);

        final List<Course> inserted =
                courses.stream()
                        .map(course -> repository.save(course))
                        .collect(Collectors.toList());
        assertEquals(courses.size(), inserted.size());

        final Course course = newCourse("course-id-1", "https://changed-vlink", "exam-id-changed", "exam-name", "exam-desc");
        repository.save(course);
        final Course courseAfterUpdate = repository.find(new CourseId("course-id-1"));

        assertEquals(courses.size(), inserted.size());
        assertEquals(courseAfterUpdate.getVideoLink(), "https://changed-vlink");
    }


    private Course newCourse(
            String id, String vlink, String examId, String examName, String examDesc) {
        return Course.create(
                new CourseId(id), vlink, new Course.Examination(examId, examName, examDesc));
    }

    private String fakeValue(String base, int count) {
        return String.format("%s-%d", base, count);
    }

    private List<Course> mockCoursesByCount(int count) {
        return IntStream.range(1, count + 1)
                .mapToObj(i -> newCourse(
                        fakeValue("course-id", i),
                        fakeValue("https://vlink", i),
                        fakeValue("exam-id", i),
                        fakeValue("exam-name", i),
                        fakeValue("exam-desc", i)))
                .collect(Collectors.toList());
    }
}
