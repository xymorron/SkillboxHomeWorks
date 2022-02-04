import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class SubscriptionKey implements Serializable {
    @Column(name = "student_id")
    private int studentId;

    @Column(name = "course_id")
    private int courseId;

    public SubscriptionKey() {

    }
    public SubscriptionKey(int studentId, int courseId) {
        this.studentId = studentId;
        this.courseId = courseId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SubscriptionKey)) return false;
        SubscriptionKey key = (SubscriptionKey) o;
        return getStudentId() == key.getStudentId() && getCourseId() == key.getCourseId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStudentId(), getCourseId());
    }
}
