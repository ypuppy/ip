//to muatate the Task status while marking, have to disable final modifier

package lumi;

import java.util.Optional;

/**
 * Represents a task with a description and completion status.
 */
public class Task {
    protected String description;
    protected boolean isDone;
    protected Optional<String> tag = Optional.empty();

    /**
     * Creates a new task with the given description.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the description of the task.
     *
     * @return The task description.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns the status icon of the task.
     *
     * @return "X" if the task is done, otherwise a space character.
     */
    public String getStatusIcon() {

        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Unmarks the task, setting it as not done.
     */
    public void unmark() {
        this.isDone = false;
    }

    /**
     * Tag an description
     * @param tag
     */
    public void setTag(String tag) {
        this.tag = Optional.of(tag);
    }

    /**
     * Get the tag
     * return empty is user did not set tag
     * @return
     */
    public String getTag() {
        return tag.orElse("");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        String tagString = tag.map(t -> " #" + t).orElse("");
        return "[" + getStatusIcon() + "] " + description;
    }
}

