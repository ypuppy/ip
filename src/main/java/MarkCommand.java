
public class MarkCommand extends Command {
    private int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws LumiException {
        if (index < 0 || index >= tasks.size()) {
            throw new LumiException("OOPS!!! The task number provided is invalid.");
        }

        Task task = tasks.get(index);
        task.markAsDone();
        storage.saveTasks(tasks.getTasks());

        ui.showMessage("Nice! I've marked this task as done:\n    " + task);
    }
}
