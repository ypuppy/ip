package lumi;

/**
 * Represents a command to add an event task to the task list.
 */
public class EventCommand extends Command {
    private Event event;

    /**
     * Creates an EventCommand with the specified event task.
     *
     * @param event The event task to be added.
     */
    public EventCommand(Event event) {
        this.event = event;
    }

    /**
     * Executes the event command by adding the event task to the task list,
     * updating storage, and displaying a message.
     *
     * @param tasks The task list to add the event to.
     * @param ui The UI instance for displaying messages.
     * @param storage The storage instance to save tasks.
     * @throws LumiException If an error occurs while saving the task.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws LumiException {
        tasks.addTask(event, ui, storage);
    }
}
