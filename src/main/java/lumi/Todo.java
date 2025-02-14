package lumi;

/**
 * Represents a simple to-do task with no specific deadline or event timing.
 */
public class Todo extends Task {

    /**
     * Creates a To-Do task with a description.
     *
     * @param description The description of the to-do task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        String tagString = getTag().isEmpty() ? "" : " #" + getTag();
        return "[T]" + super.toString() + tagString;
    }
}
