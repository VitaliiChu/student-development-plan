public class SelfStudy implements KnowledgeSource {
    private double learnPoints;
    private double practicePoints;

    public SelfStudy(double learnPoints, double practicePoints) {
        this.learnPoints = learnPoints;
        this.practicePoints = practicePoints;
    }

    @Override
    public void educate(Student student) {
        student.learn(learnPoints);
        student.practice(practicePoints);
    }
}
