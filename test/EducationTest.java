import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.util.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

class EducationTest {
    private Set<DayOfWeek> daysOfWeek;
    Set<Month> schoolMonths;

    @BeforeEach
    void setUp() {
        daysOfWeek = new HashSet<>();
        daysOfWeek.add(DayOfWeek.MONDAY);
        daysOfWeek.add(DayOfWeek.TUESDAY);
        daysOfWeek.add(DayOfWeek.WEDNESDAY);
        daysOfWeek.add(DayOfWeek.THURSDAY);
        daysOfWeek.add(DayOfWeek.FRIDAY);

        schoolMonths = new HashSet<>();
        schoolMonths.add(Month.SEPTEMBER);
        schoolMonths.add(Month.OCTOBER);
        schoolMonths.add(Month.NOVEMBER);
        schoolMonths.add(Month.DECEMBER);
        schoolMonths.add(Month.JANUARY);
        schoolMonths.add(Month.FEBRUARY);
        schoolMonths.add(Month.MARCH);
        schoolMonths.add(Month.APRIL);
        schoolMonths.add(Month.MAY);
    }

    @Test
    void universityWeekDay() {
        CompositeSchedule compositeSchedule = new CompositeSchedule();
        compositeSchedule.addSchedule(new Period(LocalDate.parse("2015-09-01"), LocalDate.parse("2020-05-29")));
        compositeSchedule.addSchedule(new StudyDayOfWeek(daysOfWeek));
        University university = new University(1, 1);
        Activity universityActivity = new Activity(compositeSchedule, university);
        List<Activity> activities = new ArrayList<>();
        activities.add(universityActivity);
        Student student1 = new Student("Tom", 0, 1, true);
        university.enrollStudents(Arrays.asList(student1));

        DevPlan devPlan = new DevPlan(activities);
        devPlan.perform(student1, new Period(LocalDate.parse("2019-11-04"), LocalDate.parse("2019-11-10")));
        assertThat(student1.getLearnPoints(), is(5.0));

        student1 = new Student("Tom", 0, 1, true);
        devPlan.perform(student1, new Period(LocalDate.parse("2019-11-04"), LocalDate.parse("2019-11-04")));
        assertThat(student1.getLearnPoints(), is(1.0));

        student1 = new Student("Tom", 0, 1, true);
        devPlan.perform(student1, new Period(LocalDate.parse("2015-01-01"), LocalDate.parse("2015-02-02")));
        assertThat(student1.getLearnPoints(), is(0.0));

        student1 = new Student("Tom", 0, 1, true);
        devPlan.perform(student1, new Period(LocalDate.parse("2015-09-01"), LocalDate.parse("2015-09-01")));
        assertThat(student1.getLearnPoints(), is(1.0));

        student1 = new Student("Tom", 0, 1, true);
        devPlan.perform(student1, new Period(LocalDate.parse("2020-05-29"), LocalDate.parse("2020-05-29")));
        assertThat(student1.getLearnPoints(), is(1.0));
    }

    @Test
    void universitySummerNotSummer() {
        CompositeSchedule compositeSchedule = new CompositeSchedule();
        compositeSchedule.addSchedule(new Period(LocalDate.parse("2015-09-01"), LocalDate.parse("2020-05-29")));
        compositeSchedule.addSchedule(new StudyDayOfWeek(daysOfWeek));
        compositeSchedule.addSchedule(new StudyMonth(schoolMonths));
        University university = new University(1, 1);
        Activity universityActivity = new Activity(compositeSchedule, university);
        List<Activity> activities = new ArrayList<>();
        activities.add(universityActivity);
        Student student1 = new Student("Tom", 0, 1, true);
        university.enrollStudents(Arrays.asList(student1));

        DevPlan devPlan = new DevPlan(activities);
        devPlan.perform(student1, new Period(LocalDate.parse("2019-06-01"), LocalDate.parse("2019-08-31")));
        assertThat(student1.getLearnPoints(), is(0.0));

        devPlan.perform(student1, new Period(LocalDate.parse("2019-11-04"), LocalDate.parse("2019-11-10")));
        assertThat(student1.getLearnPoints(), is(5.0));
    }

    @Test
    void studyAtMeetup() {
        CompositeSchedule compositeSchedule = new CompositeSchedule();
        compositeSchedule.addSchedule(new LastDayOfWeekInMonth(DayOfWeek.THURSDAY));
        Activity meetupActivity = new Activity(compositeSchedule, new Meetup(1, 2));
        List<Activity> activities = new ArrayList<>();
        activities.add(meetupActivity);

        DevPlan devPlan = new DevPlan(activities);
        Student student1 = new Student("Tom", 0, 1, true);
        Student student2 = new Student("Tom", 0, 1, false);
        devPlan.perform(student1, new Period(LocalDate.parse("2019-11-01"), LocalDate.parse("2019-11-30")));
        devPlan.perform(student2, new Period(LocalDate.parse("2019-11-01"), LocalDate.parse("2019-11-30")));
        assertThat(student1.getLearnPoints(), is(1.0));
        assertThat(student1.getPracticePoints(), is(2.0));
        assertThat(student2.getLearnPoints(), is(1.0));
        assertThat(student2.getPracticePoints(), is(0.0));
    }

    @Test
    void selfStudy() {
        CompositeSchedule compositeSchedule = new CompositeSchedule();
        compositeSchedule.addSchedule(new Period(LocalDate.parse("2019-11-01"), LocalDate.parse("2019-11-30")));
        Activity activity = new Activity(compositeSchedule, new SelfStudy(1, 1));
        List<Activity> activities = new ArrayList<>();
        activities.add(activity);

        DevPlan devPlan = new DevPlan(activities);
        Student student1 = new Student("Tom", 0, 1, true);
        devPlan.perform(student1, new Period(LocalDate.parse("2019-11-01"), LocalDate.parse("2019-11-30")));
        assertThat(student1.getLearnPoints(), is(30.0));
        assertThat(student1.getPracticePoints(), is(30.0));
    }

    @Test
    void studentShareStudy() {
        CompositeSchedule compositeSchedule = new CompositeSchedule();
        compositeSchedule.addSchedule(new Period(LocalDate.parse("2019-11-01"), LocalDate.parse("2019-11-30")));
        Activity activity = new Activity(compositeSchedule, new Student("John", 4,1, true));
        List<Activity> activities = new ArrayList<>();
        activities.add(activity);

        DevPlan devPlan = new DevPlan(activities);
        Student student1 = new Student("Tom", 0, 1, true);
        devPlan.perform(student1, new Period(LocalDate.parse("2019-11-01"), LocalDate.parse("2019-11-01")));
        assertThat(student1.getLearnPoints(), is(2.0));
    }

    @Test
    void internship() {
        CompositeSchedule compositeSchedule = new CompositeSchedule();
        compositeSchedule.addSchedule(new Period(LocalDate.parse("2019-11-01"), LocalDate.parse("2019-11-30")));
        Internship internship = new Internship(1, 2);
        Student student1 = new Student("Tom", 0, 1, true);
        internship.enrollStudents(Arrays.asList(student1));

        Activity activity = new Activity(compositeSchedule, internship);
        List<Activity> activities = new ArrayList<>();
        activities.add(activity);

        DevPlan devPlan = new DevPlan(activities);
        devPlan.perform(student1, new Period(LocalDate.parse("2019-11-01"), LocalDate.parse("2019-11-30")));
        assertThat(student1.getLearnPoints(), is(30.0));
        assertThat(student1.getPracticePoints(), is(60.0));
    }

    @Test
    void internshipEnroll() {
        CompositeSchedule compositeSchedule = new CompositeSchedule();
        compositeSchedule.addSchedule(new Period(LocalDate.parse("2019-11-01"), LocalDate.parse("2019-11-30")));
        Internship internship = new Internship(1, 2);
        Student student1 = new Student("Tom", 2, 1, true);
        Student student2 = new Student("Jerry", 2, 1, true);
        Student student3 = new Student("Ann", 4, 1, true);
        Student student4 = new Student("Linus", 4, 1, true);
        internship.enrollStudents(Arrays.asList(student1, student2, student3, student4));
        Activity activity = new Activity(compositeSchedule, internship);
        List<Activity> activities = new ArrayList<>();
        activities.add(activity);

        DevPlan devPlan = new DevPlan(activities);
        devPlan.perform(student1, new Period(LocalDate.parse("2019-11-01"), LocalDate.parse("2019-11-30")));
        devPlan.perform(student2, new Period(LocalDate.parse("2019-11-01"), LocalDate.parse("2019-11-30")));
        devPlan.perform(student3, new Period(LocalDate.parse("2019-11-01"), LocalDate.parse("2019-11-30")));
        devPlan.perform(student4, new Period(LocalDate.parse("2019-11-01"), LocalDate.parse("2019-11-30")));
        assertThat(internship.getEnrolledStudents(), hasItems(student3, student4));
        assertThat(internship.getEnrolledStudents(), not(hasItems(student1, student2)));
        assertThat(student1.getLearnPoints(), is(2.0));
        assertThat(student3.getLearnPoints(), is(34.0));
    }

    @Test
    void universityEnroll() {
        CompositeSchedule compositeSchedule = new CompositeSchedule();
        compositeSchedule.addSchedule(new Period(LocalDate.parse("2019-11-01"), LocalDate.parse("2019-11-30")));
        University university = new University(1, 2);
        Student student1 = new Student("Tom", 2, 1, true);
        Student student2 = new Student("Jerry", 2, 1, true);
        Student student3 = new Student("Ann", 4, 1, true);
        Student student4 = new Student("Linus", 4, 1, true);
        university.enrollStudents(Arrays.asList(student1, student2, student3, student4));
        Activity activity = new Activity(compositeSchedule, university);
        List<Activity> activities = new ArrayList<>();
        activities.add(activity);

        DevPlan devPlan = new DevPlan(activities);
        devPlan.perform(student1, new Period(LocalDate.parse("2019-11-01"), LocalDate.parse("2019-11-30")));
        devPlan.perform(student2, new Period(LocalDate.parse("2019-11-01"), LocalDate.parse("2019-11-30")));
        devPlan.perform(student3, new Period(LocalDate.parse("2019-11-01"), LocalDate.parse("2019-11-30")));
        devPlan.perform(student4, new Period(LocalDate.parse("2019-11-01"), LocalDate.parse("2019-11-30")));
        assertThat(university.getEnrolledStudents(), hasItems(student3, student4));
        assertThat(university.getEnrolledStudents(), not(hasItems(student1, student2)));
        assertThat(student1.getLearnPoints(), is(2.0));
        assertThat(student3.getLearnPoints(), is(34.0));
    }
}
