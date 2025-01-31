package lumi;

public class UnmarkCommand extends Command {
    private int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws LumiException {
        if (index < 0 || index >= tasks.size()) {
            throw new LumiException("OOPS!!! The task number provided is invalid.");
        }

        Task task = tasks.get(index);
        task.unmark();
        storage.saveTasks(tasks.getTasks());

        ui.showMessage("OK, I've marked this task as not done yet:\n    " + task);
    }
}
