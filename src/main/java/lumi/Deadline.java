package lumi;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a task with a deadline, storing the due date.
 */
public class Deadline extends Task {
    protected LocalDate by;

    // Constructor that accepts a LocalDate object

    /**
     * Creates a Deadline task with a description and a due date.
     *
     * @param description The description of the deadline task.
     * @param by The due date as a string in yyyy-MM-dd format.
     * @throws LumiException If the date format is invalid.
     */
    public Deadline(String description, String by) throws LumiException {
        super(description);
        try {
            this.by = LocalDate.parse(by); // Convert String to LocalDate
        } catch (DateTimeParseException e) {
            throw new LumiException("OOPS!!! Invalid date format. Please use yyyy-MM-dd (e.g., 2019-12-02).");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        // Format LocalDate as "MMM dd yyyy" (e.g., "Dec 02 2019")
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}
