public class Student implements KnowledgeSource {
    private double practicePoints;
    private double learnPoints;
    private final double educationCoefficient;
    private boolean hasNotebook;

    Student(double educationCoefficient, boolean hasNotebook) {
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
}
