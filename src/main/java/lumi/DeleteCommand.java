package lumi;

/**
 * Represents a command to delete a task from the task list.
 */
public class DeleteCommand extends Command {
    private int taskIndex;

    /**
     * Creates a DeleteCommand for a specific task index.
     *
     * @param taskIndex The index of the task to delete.
     */
    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the delete command by removing the task from the task list,
     * updating storage, and displaying a message.
     *
     * @param tasks The task list to delete the task from.
     * @param ui The UI instance for displaying messages.
     * @param storage The storage instance to save tasks.
     * @throws LumiException If an error occurs while deleting the task.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws LumiException {
        tasks.deleteTask(taskIndex, ui, storage);
    }
}

