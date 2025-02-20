package lumi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;


class DeadlineTest {

    @Test
    void testDeadlineCreation() throws LumiException {
        Deadline deadline = new Deadline("Submit report", "2023-10-15");
        assertNotNull(deadline, "Deadline should not be null.");
        assertEquals("Submit report", deadline.getDescription(), "Deadline description should match.");
        assertEquals("[D][ ] Submit report (by: Oct 15 2023)", deadline.toString(),
                "Deadline string format should be correct.");
    }

    @Test
    void testDeadlineMarkAsDone() throws LumiException {
        Deadline deadline = new Deadline("Finish assignment", "2023-12-25");
        deadline.markAsDone();
        assertEquals("[D][X] Finish assignment (by: Dec 25 2023)", deadline.toString(),
                "String representation should show deadline as done.");
    }

    @Test
    void testInvalidDateFormat() {
        Exception exception = assertThrows(LumiException.class, () -> new Deadline("Invalid date test", "15-10-2023"));
        assertTrue(exception.getMessage().contains("OOPS!!! Invalid date format"),
                "Exception should indicate invalid date format.");
    }

    @Test
    void testInvalidEmptyDate() {
        Exception exception = assertThrows(LumiException.class, () -> new Deadline("Empty date test", ""));
        assertTrue(exception.getMessage().contains("OOPS!!! Invalid date format"),
                "Exception should be thrown for empty date.");
    }

    @Test
    void testValidLeapYearDate() throws LumiException {
        Deadline deadline = new Deadline("Leap Year Task", "2024-02-29");
        assertEquals("[D][ ] Leap Year Task (by: Feb 29 2024)", deadline.toString(),
                "Leap year date should be correctly formatted.");
    }
}
