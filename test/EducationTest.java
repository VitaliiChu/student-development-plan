import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class EducationTest {
    List<Student> students;
    LocalDate initialDate;
    LocalDate finalDate;
    CompositeSchedule compositeSchedule;
    Student student1;
    List<Activity> activities;

    @BeforeEach
    void setUp() {
        initialDate = LocalDate.parse("2015-09-01");
        finalDate = LocalDate.parse("2020-05-29");
        compositeSchedule = new CompositeSchedule();

        Set<DayOfWeek> daysOfWeek = new HashSet<>();
        daysOfWeek.add(DayOfWeek.MONDAY);
        daysOfWeek.add(DayOfWeek.TUESDAY);
        daysOfWeek.add(DayOfWeek.WEDNESDAY);
        daysOfWeek.add(DayOfWeek.THURSDAY);
        daysOfWeek.add(DayOfWeek.FRIDAY);


        Period schedule = new Period(initialDate, finalDate);
        StudyDayOfWeek dayOfWeek = new StudyDayOfWeek(daysOfWeek);
        compositeSchedule.addSchedule(schedule);
        compositeSchedule.addSchedule(dayOfWeek);

        student1 = new Student(1, true);
        KnowledgeSource knowledgeSource = new University(1, 1);

        Activity activity = new Activity(compositeSchedule, knowledgeSource);
        activities = new ArrayList<>();
        activities.add(activity);
    }

    @Test
    void studyInUniversity() {
        DevPlan devPlan = new DevPlan(activities);
        devPlan.perform(student1, new Period(LocalDate.parse("2019-11-04"), LocalDate.parse("2019-11-10")));
        assertThat(student1.getLearnPoints(), is(5.0));

        student1 = new Student(1, true);
        devPlan.perform(student1, new Period(LocalDate.parse("2019-11-04"), LocalDate.parse("2019-11-04")));
        assertThat(student1.getLearnPoints(), is(1.0));

        student1 = new Student(1, true);
        devPlan.perform(student1, new Period(LocalDate.parse("2015-01-01"), LocalDate.parse("2015-02-02")));
        assertThat(student1.getLearnPoints(), is(0.0));

        student1 = new Student(1, true);
        devPlan.perform(student1, new Period(LocalDate.parse("2015-09-01"), LocalDate.parse("2015-09-01")));
        assertThat(student1.getLearnPoints(), is(1.0));

        student1 = new Student(1, true);
        devPlan.perform(student1, new Period(LocalDate.parse("2020-05-29"), LocalDate.parse("2020-05-29")));
        assertThat(student1.getLearnPoints(), is(1.0));
    }
}
