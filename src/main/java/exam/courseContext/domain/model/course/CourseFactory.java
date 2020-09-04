package exam.courseContext.domain.model.course;

public class CourseFactory {
    public static Course create(String vlink, String examId, String examName, String examDesc) {
        return Course.create(CourseId.nextId(), vlink, new Course.Examination(examId, examName, examDesc));
    }
}
