package lumi;

/**
 * Represents a command to list all tasks in the task list.
 */
public class ListCommand extends Command {

    /**
     * Executes the list command by displaying all tasks in the task list.
     *
     * @param tasks The task list containing the tasks.
     * @param ui The UI instance for displaying the task list.
     * @param storage The storage instance (not used in this command).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showMessage("OK! Here you are\n" + tasks.listTasks());
        ui.showLine();
    }
}
