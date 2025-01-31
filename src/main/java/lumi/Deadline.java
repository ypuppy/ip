package lumi;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    protected LocalDate by;

    // Constructor that accepts a LocalDate object
    public Deadline(String description, String by) throws LumiException {
        super(description);
        try {
            this.by = LocalDate.parse(by); // Convert String to LocalDate
        } catch (DateTimeParseException e) {
            throw new LumiException("OOPS!!! Invalid date format. Please use yyyy-MM-dd (e.g., 2019-12-02).");
        }
    }

    @Override
    public String toString() {
        // Format LocalDate as "MMM dd yyyy" (e.g., "Dec 02 2019")
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}
