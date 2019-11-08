import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Set;

public class MyDayOfWeek implements Schedule {
    private Set<DayOfWeek> daysOfWeek;

    public MyDayOfWeek(Set<DayOfWeek> daysOfWeek) {
        this.daysOfWeek = daysOfWeek;
    }

    @Override
    public boolean isActive(LocalDate date) {
        return daysOfWeek.contains(date.getDayOfWeek());
    }
}
