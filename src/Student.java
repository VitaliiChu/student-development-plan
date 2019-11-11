import java.util.Objects;

public class Student implements KnowledgeSource {
    private String name;
    private double practicePoints;
    private double learnPoints;
    private final double educationCoefficient;
    private boolean hasNotebook;

    public Student(String name, double learnPoints, double educationCoefficient, boolean hasNotebook) {
        this.name = name;
        this.learnPoints = learnPoints;
        this.educationCoefficient = educationCoefficient;
        this.hasNotebook = hasNotebook;
    }

    void learn(double learnPoints) {
        this.learnPoints += learnPoints * educationCoefficient;
    }

    void practice(double practicePoints) {
        this.practicePoints += practicePoints * educationCoefficient;
    }

    public double getPracticePoints() {
        return practicePoints;
    }

    public double getLearnPoints() {
        return learnPoints;
    }

    public boolean hasNotebook() {
        return hasNotebook;
    }

    @Override
    public void educate(Student student) {
        student.learn(learnPoints / 2);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(name, student.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", practicePoints=" + practicePoints +
                ", learnPoints=" + learnPoints +
                ", educationCoefficient=" + educationCoefficient +
                ", hasNotebook=" + hasNotebook +
                '}';
    }
}
