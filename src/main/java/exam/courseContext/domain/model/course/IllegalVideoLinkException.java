package exam.courseContext.domain.model.course;

public class IllegalVideoLinkException extends IllegalArgumentException {
    public IllegalVideoLinkException (String url) {
        super("IllegalVideoURLException: not support video url, actual:" + url);
    }
}
