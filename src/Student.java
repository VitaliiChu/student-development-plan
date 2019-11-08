public class Student {
    private double practicePoints;
    private double learnPoints;
    private final double educationCoefficient;

    public Student(double educationCoefficient) {
        this.educationCoefficient = educationCoefficient;
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
}
