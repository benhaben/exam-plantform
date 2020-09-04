package exam.courseContext.domain.model.course;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CourseIdTest {
    @Test
    void should_equal_when_same_id() {
        final CourseId id1 = new CourseId("1");
        final CourseId id2 = new CourseId("1");

        assertTrue(id1.sameValueAs(id2));
    }
}