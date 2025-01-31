package lumi;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    boolean isEmpty() {
        return tasks.isEmpty();
    }

    int size() {
        return tasks.size();
    }

    Task get(int index) {
        return tasks.get(index);
    }

    public void addTask(Task task, Ui ui, Storage storage) throws LumiException {
        tasks.add(task);
        storage.saveTasks(tasks);
        ui.showMessage("Added: " + task + "\nNow you have " + tasks.size() + " tasks in the list.");
    }

    public void deleteTask(int index, Ui ui, Storage storage) throws LumiException {
        if (index < 0 || index >= tasks.size()) {
            throw new LumiException("Invalid task number.");
        }
        Task removed = tasks.remove(index);
        storage.saveTasks(tasks);
        ui.showMessage("Removed: " + removed + "\nNow you have " + tasks.size() + " tasks left.");
    }

    public String listTasks() {
        if (tasks.isEmpty()) return "Your list is empty!";
        StringBuilder sb = new StringBuilder("Here are your tasks:\n");
        for (int i = 0; i < tasks.size(); i++) {
            sb.append((i + 1)).append(". ").append(tasks.get(i)).append("\n");
        }
        return sb.toString();
    }

    public ArrayList<Task> findTasks(String keyword) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }
}

