import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.time.DayOfWeek;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        List<Student> students = new ArrayList<>();
        LocalDate initialDate = LocalDate.parse("2019-11-01");
        LocalDate finalDate = LocalDate.parse("2019-11-30");

        Set<DayOfWeek> daysOfWeek = new HashSet<>();
        daysOfWeek.add(DayOfWeek.MONDAY);

        CompositeSchedule compositeSchedule = new CompositeSchedule();
        Period schedule = new Period(initialDate, finalDate);
        MyDayOfWeek dayOfWeek = new MyDayOfWeek(daysOfWeek);
        compositeSchedule.addSchedule(schedule);
        compositeSchedule.addSchedule(dayOfWeek);

        Student student1 = new Student(1);
        students.add(student1);
        KnowledgeSource knowledgeSource = new University(1, 1);

        Activity activity = new Activity(compositeSchedule, knowledgeSource);
        List<Activity> activities = new ArrayList<>();
        activities.add(activity);

        DevPlan devPlan = new DevPlan(activities);

        for (Student student : students) {
            devPlan.perform(student, new Period(LocalDate.parse("2019-11-04"), LocalDate.parse("2019-11-12")));
        }

        for (Student student : students) {
            System.out.println(student.getLearnPoints());
        }
    }
}
