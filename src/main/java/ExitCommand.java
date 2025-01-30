public class ExitCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws LumiException {
        storage.saveTasks(tasks.getTasks());  // Save before exiting
        ui.showGoodbye();
    }

    @Override
    public boolean isExit() {
        return true;  // Signals program termination
    }
}

