package exam.courseContext.domain.model.course;

import exam.courseContext.shared.Entity;
import exam.courseContext.shared.ValueObject;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@EqualsAndHashCode(of = {"id"})
public class Course implements Entity<Course> {
    private final CourseId id;
    private final LocalDateTime createTime;

    private String videoLink;
    private Examination examination;

    private CourseStatus courseStatus;
    private LocalDateTime publishTime;

    private Course(CourseId id, String videoLink, Examination examination) {
        this.id = id;
        this.videoLink = videoLink;
        this.examination = examination;
        this.courseStatus = CourseStatus.CREATED;
        this.createTime = LocalDateTime.now();
        this.publishTime = LocalDateTime.MAX;
    }

    public static Course create(CourseId id, String videoLink, Examination examination) {
        validateVideoLink(videoLink);
        return new Course(id, videoLink, examination);
    }

    public void publish() {
        courseStatus = CourseStatus.PUBLISHED;
        publishTime = LocalDateTime.now();
    }

    public void update(String videoLink) {
        validateVideoLink(videoLink);
        this.videoLink = videoLink;
    }

    public void update(Examination examination) {
        this.examination = examination;
    }

    public void update(String videoLink, Examination examination) {
        update(videoLink);
        update(examination);
    }

    public static void validateVideoLink(String link) {
        String[] schemes = {"http://", "https://"};

        for (String schema : schemes) {
            if (link.startsWith(schema)) {
                return;
            }
        }
        throw new IllegalVideoLinkException(link);
    }

    @Override
    public boolean sameIdentityAs(Course other) {
        return id.sameValueAs(other.id);
    }

    @Getter
    @AllArgsConstructor
    public static class Examination implements ValueObject<Examination> {
        private final String id;
        private final String name;
        private final String description;

        @Override
        public boolean sameValueAs(Examination other) {
            return equals(other);
        }
    }
}
