package lumi;

/**
 * Represents a command to clear all tasks from the list and storage.
 */
public class ClearCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.clear(ui, storage); // Calls TaskList.clear()
    }
}
