package lumi;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws LumiException;
    public boolean isExit() {
        return false;  // Default is false; overridden by ExitCommand.
    }
}
