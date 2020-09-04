package exam.courseContext.domain.model.course;

public class NotFoundCourseException extends RuntimeException {
    public NotFoundCourseException(String courseId) {
        super("NotFoundCourseException: not found course by " + courseId);
    }
}
