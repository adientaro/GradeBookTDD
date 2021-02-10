import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SubjectTest {

    private final String defaultSubjectName = "Math";
    @Test
    public void subjectsWithTheSameNamesAreEqual(){
        Subject subject1 = new Subject(defaultSubjectName);
        Subject subject2 = new Subject(defaultSubjectName);
        assertEquals(subject1, subject2);
    }

    @Test
    public void subjectsWithDifferentNamesAreNotEqual(){
        Subject subject1 = new Subject(defaultSubjectName);
        Subject subject2 = new Subject("Geography");
        assertNotEquals(subject1, subject2);
    }

    @Test
    public void subjectsWithTheSameNamesAndGradesAreEqual(){
        Subject subject1 = new Subject(defaultSubjectName, 2.0, 5.0, 4.0, 1.0);
        Subject subject2 = new Subject(defaultSubjectName, 2.0, 5.0, 4.0, 1.0);
        assertEquals(subject1, subject2);
    }

    @Test
    public void subjectsWithTheSameNamesAndDifferentGradesAreNotEqual(){
        Subject subject1 = new Subject(defaultSubjectName, 2.0, 5.0, 5.0, 1.0);
        Subject subject2 = new Subject(defaultSubjectName, 2.0, 5.0, 4.0, 1.0);
        assertNotEquals(subject1, subject2);
    }

    @Test
    public void shouldThrowExceptionWhenTryToAddItemWithEmptyName() {
        assertThrows(IllegalArgumentException.class, () -> new Subject(""));
    }

    @Test
    public void shouldThrowExceptionWhenTryToAddItemWithNullName() {
        assertThrows(IllegalArgumentException.class, () -> new Subject(null));
    }


}
