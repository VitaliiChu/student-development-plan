public class Meetup implements KnowledgeSource {
    private double learnPoints;
    private double practicePoints;

    public Meetup(double learnPoints, double practicePoints) {
        this.learnPoints = learnPoints;
        this.practicePoints = practicePoints;
    }

    @Override
    public void educate(Student student) {
        student.learn(learnPoints);
        if (student.hasNotebook()) {
            student.practice(practicePoints);
        }
    }
}
