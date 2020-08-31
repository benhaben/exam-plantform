package exam.courseContext.shared;

public interface Entity<T> {
    boolean sameIdentityAs(T other);
}
