package lumi;
/**
 * Represents an abstract command that can be executed.
 */
public abstract class Command {
    /**
     * Executes the command, modifying the task list and interacting with the UI and storage.
     *
     * @param tasks The task list to operate on.
     * @param ui The UI instance for displaying messages.
     * @param storage The storage instance to save tasks.
     * @throws LumiException If an error occurs while executing the command.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws LumiException;

    /**
     * Determines whether the command should cause the application to exit.
     *
     * @return {@code false} by default; should be overridden by ExitCommand to return {@code true}.
     */
    public boolean isExit() {
        return false;
    }
}
