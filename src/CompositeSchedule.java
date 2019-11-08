import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CompositeSchedule implements Schedule {
    private List<Schedule> schedules = new ArrayList<>();

    @Override
    public boolean isActive(LocalDate date) {
        for (Schedule schedule : schedules) {
            if (!schedule.isActive(date)) {
                return false;
            }
        }
        return true;
    }

    void addSchedule(Schedule schedule) {
        this.schedules.add(schedule);
    }

    public List<Schedule> getSchedules() {
        return schedules;
    }
}
