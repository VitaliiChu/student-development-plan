import java.time.LocalDate;
import java.time.Month;
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

        Set<Month> schoolMonths = new HashSet<>();
        schoolMonths.add(Month.SEPTEMBER);
        schoolMonths.add(Month.OCTOBER);
        schoolMonths.add(Month.NOVEMBER);
        schoolMonths.add(Month.DECEMBER);
        schoolMonths.add(Month.JANUARY);
        schoolMonths.add(Month.FEBRUARY);
        schoolMonths.add(Month.MARCH);
        schoolMonths.add(Month.APRIL);
        schoolMonths.add(Month.MAY);

        CompositeSchedule compositeSchedule = new CompositeSchedule();
        Period schedule = new Period(initialDate, finalDate);
        StudyDayOfWeek dayOfWeek = new StudyDayOfWeek(daysOfWeek);
        StudyMonth studyMonth = new StudyMonth(schoolMonths);
        compositeSchedule.addSchedule(schedule);
        compositeSchedule.addSchedule(dayOfWeek);
        compositeSchedule.addSchedule(studyMonth);

        Student student1 = new Student("Tom", 0, 1, true);
        students.add(student1);
        University university = new University(1, 1);
        university.enrollStudents(students);

        Activity activity = new Activity(compositeSchedule, university);
        List<Activity> activities = new ArrayList<>();
        activities.add(activity);

        DevPlan devPlan = new DevPlan(activities);

        for (Student student : students) {
            devPlan.perform(student, new Period(LocalDate.parse("2019-11-04"), LocalDate.parse("2019-11-05")));
        }

        for (Student student : students) {
            System.out.println(student.getLearnPoints());
        }
    }
}
