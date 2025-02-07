package lumi;

/**
 * Represents a command that displays a welcome message and the user's previous tasks.
 */
public class WelcomeCommand extends Command {

    /**
     * Executes the welcome command by displaying a greeting message and listing existing tasks.
     *
     * @param tasks   The task list containing previously saved tasks.
     * @param ui      The UI instance for displaying messages.
     * @param storage The storage instance to persist data (not used in this command).
     * @throws LumiException If an error occurs while executing the command.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws LumiException {
        ui.showMessage("Hi, Lumi here, any changes to your tasklist?\nhere are your previous tasks\n\n"
                + tasks.listTasks());
    }
}
