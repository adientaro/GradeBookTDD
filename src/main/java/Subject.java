import java.util.*;

public class Subject {
    private String subjectName;
    private List<Double> grades;

    public String getSubjectName() {
        return subjectName;
    }

    public List<Double> getGrades() {
        return grades;
    }

    public Subject(String subjectName) {
        if (subjectName == null || subjectName.isBlank()) {
            throw new IllegalArgumentException("Subject cannot be blank");
        }
        this.subjectName = subjectName;
        this.grades = new ArrayList<>();
    }

    public Subject(String subjectName, Double ... grades) {
        this(subjectName);
        this.grades = Arrays.asList(grades);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Subject subject = (Subject) o;
        return subjectName.equals(subject.subjectName) &&
                grades.equals(subject.grades);
    }

    @Override
    public int hashCode() {
        return Objects.hash(subjectName, grades);
    }
}
