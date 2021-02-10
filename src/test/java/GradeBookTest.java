import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.stream.DoubleStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class GradeBookTest {
    private final String defaultSubjectName = "Math";
    private final String defaultSubjectName2 = "Geography";


    @BeforeEach
    public void setUp() {
    }

    @Test
    void shouldAddOneSubjectToGradeBookWithoutGrades(){
        GradeBook gradeBook = new GradeBook(defaultSubjectName);
        var returnedResult = gradeBook.getSubject(defaultSubjectName);
        var expectedResult = new Subject(defaultSubjectName);
        assertEquals(expectedResult,returnedResult);
    }

    @Test
    void shouldAddMultipleSubjectWithoutGrades(){
        GradeBook gradeBook = new GradeBook(defaultSubjectName, defaultSubjectName2);
        var returnedResult = gradeBook.getAll();
        var expectedResult = new ArrayList<>();
        expectedResult.add( new Subject(defaultSubjectName));
        expectedResult.add( new Subject(defaultSubjectName2));
        assertEquals(expectedResult,returnedResult);
    }
    @Test
    void shouldGetSubjectByTheName(){
        GradeBook gradeBook = new GradeBook(defaultSubjectName, defaultSubjectName2);
        gradeBook.addGradesToSubject(defaultSubjectName, 2.5, 4.0);
        gradeBook.addGradesToSubject(defaultSubjectName2, 2.5, 5.0);
        var returnedResult = gradeBook.getSubject("Math");
        var expectedResult = new Subject(defaultSubjectName, 2.5, 4.0);
        assertEquals(expectedResult,returnedResult);
    }

    @Test
    void shouldThrowExceptionWhenCouldNotFindSubject(){
        GradeBook gradeBook = new GradeBook(defaultSubjectName, defaultSubjectName2);
        gradeBook.addGradesToSubject(defaultSubjectName, 2.5, 4.0);
        gradeBook.addGradesToSubject(defaultSubjectName2, 2.5, 5.0);
        assertThrows(IllegalArgumentException.class, () -> gradeBook.getSubject("xyz"));
    }

    @Test
    void shouldAddOneGradeToSubject(){
        GradeBook gradeBook = new GradeBook(defaultSubjectName, defaultSubjectName2);
        gradeBook.addGradesToSubject(defaultSubjectName, 2.0);
        var returnedResult = gradeBook.getSubject("Math");
        var expectedResult = new Subject("Math", 2.0);
        assertEquals(expectedResult,returnedResult);
    }

    @Test
    void shouldComputeAverageForSubject() {
        GradeBook gradeBook = new GradeBook(defaultSubjectName, defaultSubjectName2);
        gradeBook.addGradesToSubject(defaultSubjectName, 2.0, 5.0, 6.0);
        var expectedResult =  DoubleStream.of(2.0, 5.0, 6.0).average().getAsDouble();
        var returnedResult = gradeBook.calcluateAverage(defaultSubjectName);
        assertEquals(expectedResult, returnedResult);
    }

    @Test
    void shouldComputeAverageForGradeBook() {
        GradeBook gradeBook = new GradeBook(defaultSubjectName, defaultSubjectName2);
        gradeBook.addGradesToSubject(defaultSubjectName, 2.5, 4.0);
        gradeBook.addGradesToSubject(defaultSubjectName2, 2.5, 5.0);
        var expectedResult =  DoubleStream.of(2.5, 4.0, 2.5, 5.0).average().getAsDouble();
        var returnedResult = gradeBook.calcluateAverage();
        assertEquals(expectedResult, returnedResult);
    }

    @Test
    void shouldThrowWhenTryToCalculateAverageFromGradeBookWithoutGrades() {
        GradeBook gradeBook = new GradeBook(defaultSubjectName, defaultSubjectName2);
        assertThrows(ArithmeticException.class, () -> gradeBook.calcluateAverage());
    }

    @Test
    void shouldThrowWhenTryToCalculateAverageFromEmptyGradeBook() {
        GradeBook gradeBook = new GradeBook();
        assertThrows(ArithmeticException.class, () -> gradeBook.calcluateAverage());
    }

    @Test
    void shouldThrowWhenTryToCalculateAverageFromSubjectWithoutGrades() {
        GradeBook gradeBook = new GradeBook(defaultSubjectName, defaultSubjectName2);
        assertThrows(ArithmeticException.class, () -> gradeBook.calcluateAverage(defaultSubjectName));
    }

    @Test
    void shouldThrowExceptionWhenTryToGetSubjectByNullName(){
        GradeBook gradeBook = new GradeBook();
        assertThrows(UnsupportedOperationException.class, () -> gradeBook.getSubject(null));
    }

}
