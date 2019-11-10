import java.time.LocalDate;
import java.util.List;

public class DevPlan {
    private List<Activity> activities;

    DevPlan(List<Activity> activities) {
        this.activities = activities;
    }

    void perform(Student student, Period period) {
        LocalDate currentDate = period.getInitialDate();

        while (currentDate.isBefore(period.getFinalDate().plusDays(1))) {
            for (Activity activity : activities) {
                activity.tryToApply(student, currentDate);
            }
            currentDate = currentDate.plusDays(1);
        }
    }
}
