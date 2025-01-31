package lumi;

import java.util.ArrayList;

/**
 * Manages a list of tasks, providing operations to add, delete, and retrieve tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Initializes an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Initializes a TaskList with an existing list of tasks.
     *
     * @param tasks The initial list of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Checks if the task list is empty.
     *
     * @return {@code true} if the task list is empty, otherwise {@code false}.
     */
    boolean isEmpty() {
        return tasks.isEmpty();
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return The size of the task list.
     */
    int size() {
        return tasks.size();
    }

    /**
     * Retrieves a task at the specified index.
     *
     * @param index The index of the task.
     * @return The task at the specified index.
     */
    Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Adds a new task to the list, updates storage, and displays a message.
     *
     * @param task The task to add.
     * @param ui The UI instance for displaying messages.
     * @param storage The storage instance to save tasks.
     * @throws LumiException If an error occurs while saving tasks.
     */
    public void addTask(Task task, Ui ui, Storage storage) throws LumiException {
        tasks.add(task);
        storage.saveTasks(tasks);
        ui.showMessage("Added: " + task + "\nNow you have " + tasks.size() + " tasks in the list.");
    }

    /**
     * Deletes a task from the list, updates storage, and displays a message.
     *
     * @param index The index of the task to delete.
     * @param ui The UI instance for displaying messages.
     * @param storage The storage instance to save tasks.
     * @throws LumiException If an invalid task number is provided.
     */
    public void deleteTask(int index, Ui ui, Storage storage) throws LumiException {
        if (index < 0 || index >= tasks.size()) {
            throw new LumiException("Invalid task number.");
        }
        Task removed = tasks.remove(index);
        storage.saveTasks(tasks);
        ui.showMessage("Removed: " + removed + "\nNow you have " + tasks.size() + " tasks left.");
    }

    /**
     * Returns a string representation of all tasks in the list.
     *
     * @return A formatted string listing all tasks, or a message if the list is empty.
     */
    public String listTasks() {
        if (tasks.isEmpty()) return "Your list is empty!";
        StringBuilder sb = new StringBuilder("Here are your tasks:\n");
        for (int i = 0; i < tasks.size(); i++) {
            sb.append("    ").append((i + 1)).append(". ").append(tasks.get(i)).append("\n");
        }
        return sb.toString();
    }

    /**
     * Finds tasks that contain a specific keyword.
     *
     * @param keyword The keyword to search for.
     * @return A list of tasks that contain the keyword.
     */
    public ArrayList<Task> findTasks(String keyword) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }

    /**
     * Returns the list of tasks.
     *
     * @return The ArrayList of tasks.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }
}

