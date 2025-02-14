package lumi;

/**
 * Creates an TagCommand to tag the specified task.
 */
public class TagCommand extends Command {
    private final int taskIndex;
    private final String tag;

    /**
     * Constructor of TagCommand
     * @param taskIndex
     * @param tag
     */
    public TagCommand(int taskIndex, String tag) {
        this.taskIndex = taskIndex;
        this.tag = tag;
    }
    /**
     * Executes the  TagCommand by set tag to the task from the task list
     * @param tasks The task list to delete the task from.
     * @param ui The UI instance for displaying messages.
     * @param storage The storage instance to save tasks.
     * @throws LumiException If an error occurs while deleting the task.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws LumiException {
        if (taskIndex < 0 || taskIndex >= tasks.size()) {
            throw new LumiException("OOPS!!! Invalid task number.");
        }

        Task task = tasks.get(taskIndex);
        task.setTag(tag);
        storage.saveTasks(tasks.getTasks());
        ui.showMessage("Tagged task: " + task);
    }
}
