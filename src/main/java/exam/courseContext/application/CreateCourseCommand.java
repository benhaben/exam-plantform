package exam.courseContext.application;

import lombok.Value;

@Value
public class CreateCourseCommand {
    String videoLink;
    Examination examination;

    @Value
    public static class Examination {
        String id;
        String name;
        String description;
    }
}
