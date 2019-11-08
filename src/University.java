public class University implements KnowledgeSource {
    double learnPoints;
    double practicePoints;

    public University(double learnPoints, double practicePoints) {
        this.learnPoints = learnPoints;
        this.practicePoints = practicePoints;
    }

    @Override
    public void educate(Student student) {
        student.learn(learnPoints);
        student.practice(practicePoints);
    }
}
