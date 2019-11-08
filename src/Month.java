import java.time.LocalDate;

public class Month implements Schedule {
    LocalDate date;

    public Month(LocalDate date) {
        this.date = date;
    }

    @Override
    public boolean isActive(LocalDate date) {
        return false;
    }
}
