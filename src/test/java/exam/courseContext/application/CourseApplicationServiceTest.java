package exam.courseContext.application;

import exam.courseContext.domain.model.course.Course;
import exam.courseContext.domain.model.course.CourseId;
import exam.courseContext.domain.model.course.CourseRepository;
import exam.courseContext.infrastructure.MemoryCourseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CourseApplicationServiceTest {
    private CourseRepository courseRepository;
    private CourseApplicationService courseApplicationService;

    @BeforeEach
    void setUp() {
        courseRepository = new MemoryCourseRepository();
        courseApplicationService = new CourseApplicationService(courseRepository);
    }

    @Test
    void should_create_new_course_when_repository_is_empty() {
        final String vlink = "https://vlink";
        final String examId = "exam-id";
        final String examName = "exam-name";
        final String examDesc = "exam-desc";

        Course course =
                courseApplicationService.createCourse(
                        new CreateCourseCommand(
                                vlink,
                                new CreateCourseCommand.Examination(examId, examName, examDesc)));

        assertEquals(course.getVideoLink(), vlink);
    }

    @Test
    void should_update_correctly_when_has_same_id() {
        final String id = "id";
        final String vlink = "https://vlink";
        final String examId = "exam-id";
        final String examName = "exam-name";
        final String examDesc = "exam-desc";

        courseRepository.save(
                Course.create(
                        new CourseId(id),
                        vlink,
                        new Course.Examination(examId, examName, examDesc)));

        final Course course = courseApplicationService.updateCourse(
                id,
                new CreateCourseCommand(
                        "https://changed-vlink",
                        new CreateCourseCommand.Examination(examId, examName, examDesc)));

        assertEquals(course.getVideoLink(), "https://changed-vlink");
    }
}
