package lumi;

/**
 * Represents a command to unmark a task as not done.
 */
public class UnmarkCommand extends Command {
    private int index;

    /**
     * Creates an UnmarkCommand for a specific task index.
     *
     * @param index The index of the task to unmark.
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the unmark command by setting the task as not done,
     * updating storage, and displaying a confirmation message.
     *
     * @param tasks The task list containing the task.
     * @param ui The UI instance for displaying messages.
     * @param storage The storage instance to save tasks.
     * @throws LumiException If the task index is invalid.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws LumiException {
        if (index < 0 || index >= tasks.size()) {
            throw new LumiException("OOPS!!! The task number provided is invalid.");
        }

        Task task = tasks.get(index);
        task.unmark();
        storage.saveTasks(tasks.getTasks());

        ui.showMessage("OK, I've marked this task as not done yet:\n    " + (index + 1) + ". " + task);
    }
}
