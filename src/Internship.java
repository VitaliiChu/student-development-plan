import java.util.List;

public class Internship implements KnowledgeSource {
    private double learnPoints;
    private double practicePoints;
    private List<Student> enrolledStudents;

    Internship(double learnPoints, double practicePoints) {
        this.learnPoints = learnPoints;
        this.practicePoints = practicePoints;
    }

    @Override
    public void educate(Student student) {
        if (enrolledStudents.contains(student)) {
            student.learn(learnPoints);
            student.practice(practicePoints);
        }
    }

    public void enrollStudents(List<Student> students) {
        Enrollment enrollment = new Enrollment();
        this.enrolledStudents = enrollment.enrollStudents(students);
    }

    public List<Student> getEnrolledStudents() {
        return enrolledStudents;
    }
}
