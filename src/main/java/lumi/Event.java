package lumi;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents an event task with a specific start and end time.
 */
public class Event extends Task {
    protected LocalDate from;
    protected LocalDate to;

    /**
     * Creates an Event task with a description, start time, and end time.
     *
     * @param description The description of the event.
     * @param from The start time of the event.
     * @param to The end time of the event.
     */
    public Event(String description, String from, String to) throws LumiException {
        super(description);
        try {
            this.from = LocalDate.parse(from);
            this.to = LocalDate.parse(to);
        } catch (DateTimeParseException e) {
            throw new LumiException("Please use yyyy-MM-dd for event dates (e.g., 2019-12-02).");
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        String tagString = getTag().isEmpty() ? "" : " #" + getTag();
        return "[E]" + super.toString() + " (from: "
                + from.format(DateTimeFormatter.ofPattern("MMM dd yyyy"))
                + " to: "
                + to.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")"
                + tagString;
    }
}
