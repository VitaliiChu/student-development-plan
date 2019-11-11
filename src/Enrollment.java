import java.util.ArrayList;
import java.util.List;

public class Enrollment {
    public List<Student> enrollStudents(List<Student> students) {
        List<Student> enrolledStudents = new ArrayList<>();

        double averageKnowledge = getAverageKnowledge(students);
        for (Student student : students) {
            if (student.getLearnPoints() >= averageKnowledge) {
                enrolledStudents.add(student);
            }
        }

        return enrolledStudents;
    }

    private double getAverageKnowledge(List<Student> students) {
        double sumOfKnowledge = 0;

        for (Student student : students) {
            sumOfKnowledge += student.getLearnPoints();
        }

        return sumOfKnowledge/students.size();
    }
}
