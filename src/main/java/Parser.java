
public class Parser {
    // Parses user input and returns a corresponding Command object
    public static Command parse(String input) throws LumiException {
        String[] words = input.split(" ", 2);
        String commandWord = words[0];

        switch (commandWord) {
            case "bye":
                return new ExitCommand();
            case "list":
                return new ListCommand();
            case "todo":
                if (words.length < 2 || words[1].trim().isEmpty()) {
                    throw new LumiException("OOPS!!! The description of a todo cannot be empty.");
                }
                return new AddCommand(new Todo(words[1].trim()));
            case "deadline":
                if (words.length < 2 || !words[1].contains("/by")) {
                    throw new LumiException("OOPS!!! Deadline task requires a description and a date.");
                }
                String[] deadlineParts = words[1].split("/by", 2);
                return new AddCommand(new Deadline(deadlineParts[0].trim(), deadlineParts[1].trim()));
            case "delete":
                if (words.length < 2) {
                    throw new LumiException("OOPS!!! Please specify a task number to delete.");
                }
                return new DeleteCommand(Integer.parseInt(words[1].trim()) - 1);
            case "event":
                if (words.length < 2 || !words[1].contains("/from") || !words[1].contains("/to")) {
                    throw new LumiException("OOPS!!! Event must have a description, start time, and end time.");
                }
                String[] eventParts = words[1].split("/from", 2);
                String[] timeParts = eventParts[1].split("/to", 2);
                return new AddCommand(new Event(eventParts[0].trim(), timeParts[0].trim(), timeParts[1].trim()));
            case "unmark":
                if (words.length < 2) {
                    throw new LumiException("OOPS!!! Please specify a task number to unmark.");
                }
                return new UnmarkCommand(Integer.parseInt(words[1].trim()) - 1);
            default:
                throw new LumiException("OOPS!!! I'm sorry, but I don't understand that command.");
        }
    }

    // Processes the command and execserutes it
    public static boolean processCommand(String input, TaskList tasks, Ui ui, Storage storage) throws LumiException {
        Command command = parse(input);
        command.execute(tasks, ui, storage);
        return !command.isExit(); // If ExitCommand is executed, this will return false and stop the loop.
    }
}
