import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class GradeBook {

    private final static String notSubjectFoundMessage = "Cannot get empty subject";
    private final List<Subject> gradeBook = new ArrayList<>();

    public GradeBook(String... subjectNames) {
        addSubjectNames(subjectNames);
    }

    public void addSubject(Subject subject) {
        gradeBook.add(subject);
    }


    public void addSubjectNames(String... names) {
        for (String name : names) {
            Subject subject = new Subject(name);
            gradeBook.add(subject);
        }
    }

    public Subject getSubject(String name) {
        if (name == null || name.isBlank()) {
            throw new UnsupportedOperationException(notSubjectFoundMessage);
        }
        var result = gradeBook.stream().filter(subject -> name.equals(subject.getSubjectName())).findFirst();
        if (result.isPresent()) {
            return result.get();
        } else {
            throw new IllegalArgumentException("Cannot find specified subject");
        }
    }

    public List<Subject> getAll() {
        return Collections.unmodifiableList(gradeBook);
    }

    public void addGradesToSubject(String name, Double... v) {
        var subject = getSubject(name);
        subject.getGrades().addAll(Arrays.asList(v));
    }

    public double calcluateAverage(String subjectName) {
        var subject = getSubject(subjectName);
        var avg = subject.getGrades().stream().mapToDouble(x -> x).average();
        if (avg.isPresent()) {
            return avg.getAsDouble();
        } else {
            throw new ArithmeticException("Cannot calcluate average from empty list of grades");
        }
    }

    public double calcluateAverage() {
        var avg = gradeBook.stream().map(subject -> calcluateAverage(subject.getSubjectName())).mapToDouble(x -> x).average();
        if (avg.isPresent()) {
            return avg.getAsDouble();
        } else {
            throw new ArithmeticException("Cannot calcluate average from empty list of grades");
        }
    }
}
