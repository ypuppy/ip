package lumi;

/**
 * Represents a command to mark a task as done.
 */
public class MarkCommand extends Command {
    private int index;

    /**
     * Creates a MarkCommand for a specific task index.
     *
     * @param index The index of the task to mark as done.
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the mark command by marking the task as done, updating storage,
     * and displaying a confirmation message.
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
        task.markAsDone();
        storage.saveTasks(tasks.getTasks());

        ui.showMessage("Nice! I've marked this task as done:\n    " + (index + 1) + ". " + task);
    }
}
