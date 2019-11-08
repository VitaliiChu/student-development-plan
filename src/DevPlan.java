import java.time.LocalDate;
import java.util.List;

public class DevPlan {
    List<Activity> activities;

    public DevPlan(List<Activity> activities) {
        this.activities = activities;
    }

    void perform(Student student, Period period) {
        LocalDate currentDate = period.getInitialDate();

        while (currentDate.isBefore(period.getFinalDate())) {
            for (Activity activity : activities) {
                activity.tryToApply(student, currentDate);
            }
            currentDate = currentDate.plusDays(1);
        }
    }
}
