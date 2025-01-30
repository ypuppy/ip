public class DeleteCommand extends Command {
    private int taskIndex;

    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws LumiException {
        tasks.deleteTask(taskIndex, ui, storage);
    }
}

