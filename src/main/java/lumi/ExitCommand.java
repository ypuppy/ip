package lumi;

/**
 * Represents a command to exit the application.
 */
public class ExitCommand extends Command {
    @Override

    /*
     * Executes the exit command by saving tasks and displaying a goodbye message.
     *
     * @param tasks The task list to save before exiting.
     * @param ui The UI instance for displaying the goodbye message.
     * @param storage The storage instance to save tasks.
     * @throws LumiException If an error occurs while saving tasks.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws LumiException {
        storage.saveTasks(tasks.getTasks());
        ui.showGoodbye();
    }

    /**
     * Indicates that this command signals program termination.
     *
     * @return {@code true}, as this command exits the application.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}

