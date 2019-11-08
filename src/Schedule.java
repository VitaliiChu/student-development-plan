import java.time.LocalDate;

public interface Schedule {
    boolean isActive(LocalDate date);
}
