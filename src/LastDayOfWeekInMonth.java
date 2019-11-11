import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

public class LastDayOfWeekInMonth implements Schedule {
    private DayOfWeek dayOfWeek;

    public LastDayOfWeekInMonth(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    @Override
    public boolean isActive(LocalDate date) {
        LocalDate lastDay = date.with(TemporalAdjusters.lastInMonth(dayOfWeek));
        return date.isEqual(lastDay);
    }
}
