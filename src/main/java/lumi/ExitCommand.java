package lumi;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.util.Duration;

/**
 * Represents a command to exit the application.
 */
public class ExitCommand extends Command {
    private static final int DELAY = 1;
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
        PauseTransition delay = new PauseTransition(Duration.seconds(DELAY));
        delay.setOnFinished(event -> Platform.exit());
        delay.play();
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

