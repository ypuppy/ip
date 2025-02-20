package lumi;

import java.util.ArrayList;

/**
 * Represents a command to find tasks containing a specific keyword.
 */
public class FindCommand extends Command {
    private final String keyword;

    /**
     * Creates a FindCommand with the specified keyword.
     *
     * @param keyword The keyword to search for in tasks.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the find command by searching for tasks containing the keyword,
     * and displaying the matching tasks.
     *
     * @param tasks The task list to search through.
     * @param ui The UI instance for displaying messages.
     * @param storage The storage instance (not used in this command).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> matchingTasks = tasks.findTasks(keyword);

        if (matchingTasks.isEmpty()) {
            ui.showMessage("No tasks found matching: " + keyword);
        } else {
            StringBuilder result = new StringBuilder("Here are the matching tasks in your list :):\n");
            for (int i = 0; i < matchingTasks.size(); i++) {
                result.append((i + 1)).append(". ").append(matchingTasks.get(i)).append("\n");
            }
            ui.showMessage(result.toString());
        }
    }
}

