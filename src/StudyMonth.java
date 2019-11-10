import java.time.LocalDate;
import java.time.Month;
import java.util.Set;

public class StudyMonth implements Schedule {
    Set<Month> months;

    public StudyMonth(Set<Month> months) {
        this.months = months;
    }

    @Override
    public boolean isActive(LocalDate date) {
        return months.contains(date.getMonth());
    }
}
