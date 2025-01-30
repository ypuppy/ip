public class Parser {
    public boolean processCommand(String input, TaskList tasks, Ui ui, Storage storage) throws LumiException {
        if (input.equals("bye")) {
            storage.saveTasks(tasks.getTasks());
            return false;
        } else if (input.equals("list")) {
            ui.showMessage(tasks.listTasks());
        } else if (input.startsWith("todo ")) {
            String description = input.substring(5).trim();
            tasks.addTask(new Todo(description), ui, storage);
        } else if (input.startsWith("deadline ")) {
            String[] parts = input.substring(9).split("/by\\s*");
            if (parts.length < 2) throw new LumiException("Deadline task requires a date.");
            tasks.addTask(new Deadline(parts[0].trim(), parts[1].trim()), ui, storage);
        } else if (input.startsWith("delete ")) {
            int index = Integer.parseInt(input.substring(7).trim()) - 1;
            tasks.deleteTask(index, ui, storage);
        } else {
            throw new LumiException("I'm sorry, but I don't understand that command.");
        }
        return true;
    }
}

