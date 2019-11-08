import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EducationTest {
    List<Student> students;
    LocalDate initialDate;
    LocalDate finalDate;
    CompositeSchedule compositeSchedule;

    @BeforeEach
    void setUp() {
        students = new ArrayList<>();
        initialDate = LocalDate.parse("2007-05-10");
        finalDate = LocalDate.parse("2007-05-20");
        compositeSchedule = new CompositeSchedule();


        Period schedule = new Period(initialDate, finalDate);
        compositeSchedule.addSchedule(schedule);

        Student student1 = new Student(1);
        students.add(student1);
        KnowledgeSource knowledgeSource = new University(1, 1);

        Activity activity = new Activity(compositeSchedule, knowledgeSource);
        List<Activity> activities = new ArrayList<>();
        activities.add(activity);
    }

    @Test
    void periodInUniversity() {

    }
}
