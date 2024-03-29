import java.time.LocalDate;

public class Activity {
    private CompositeSchedule compositeSchedule;
    private KnowledgeSource knowledgeSource;

    Activity(CompositeSchedule compositeSchedule, KnowledgeSource knowledgeSource) {
        this.compositeSchedule = compositeSchedule;
        this.knowledgeSource = knowledgeSource;
    }

    void tryToApply(Student student, LocalDate date) {
        if (compositeSchedule.isActive(date)) {
            knowledgeSource.educate(student);
        }
    }
}
