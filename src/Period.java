import java.time.LocalDate;

public class Period implements Schedule {
    private LocalDate initialDate;
    private LocalDate finalDate;

    Period(LocalDate initialDate, LocalDate finalDate) {
        this.initialDate = initialDate;
        this.finalDate = finalDate;
    }

    @Override
    public boolean isActive(LocalDate date) {
        return !date.isBefore(initialDate) && !date.isAfter(finalDate);
    }

    public LocalDate getInitialDate() {
        return initialDate;
    }

    public LocalDate getFinalDate() {
        return finalDate;
    }
}
