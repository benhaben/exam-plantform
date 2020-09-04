package exam.courseContext.domain.model.course;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CourseTest {

    @Test
    void should_create_course_ok() {
        CourseId id = new CourseId("course-id-1");
        String vlink = "https://vlink";
        Course.Examination examination =
                new Course.Examination("exam-id", "exam-name", "exam-desc");

        Course course = Course.create(id, vlink, examination);

        assertNotNull(course);
        assertEquals(course.getCourseStatus(), CourseStatus.CREATED);
    }

    @Test
    void should_status_published_when_publish_course() {
        CourseId id = new CourseId("course-id-1");
        String vlink = "https://vlink";
        Course.Examination examination =
                new Course.Examination("exam-id", "exam-name", "exam-desc");

        Course course = Course.create(id, vlink, examination);
        course.publish();

        assertNotNull(course);
        assertEquals(course.getCourseStatus(), CourseStatus.PUBLISHED);
    }

    @Test
    void should_update_video_link_ok() {
        CourseId id = new CourseId("course-id-1");
        String vlink = "https://vlink";
        Course.Examination examination =
                new Course.Examination("exam-id", "exam-name", "exam-desc");

        Course.Examination exam2 =
                new Course.Examination("exam-2-id", "exam-2-name", "exam-2-desc");

        final Course course = Course.create(id, vlink, examination);
        course.update("https://vlink-2", exam2);

        assertNotNull(course);
        assertEquals(course.getVideoLink(), "https://vlink-2");
        assertEquals(course.getExamination().getId(), exam2.getId());
    }

    @Test
    void should_raise_illegal_video_link_exception_when_update_course() {
        assertThrows(
                IllegalVideoLinkException.class,
                () -> {
                    CourseId id = new CourseId("course-id-1");
                    String vlink = "https://vlink";
                    Course.Examination examination =
                            new Course.Examination("exam-id", "exam-name", "exam-desc");

                    final Course course = Course.create(id, vlink, examination);
                    course.update("ftp://vlink-invalid");
                });
    }

    @Test
    void should_raise_illegal_video_link_exception() {
        assertThrows(
                IllegalVideoLinkException.class,
                () -> {
                    CourseId id = new CourseId("course-id-1");
                    String vlink = "ftp://vlink";
                    Course.Examination examination =
                            new Course.Examination("exam-id", "exam-name", "exam-desc");

                    final Course course = Course.create(id, vlink, examination);

                    assertNotNull(course);
                    assertEquals(course.getVideoLink(), "https://vlink-2");
                });
    }
}
