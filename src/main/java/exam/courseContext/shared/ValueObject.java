package exam.courseContext.shared;

public interface ValueObject<T> {
    boolean sameValueAs(T other);
}
