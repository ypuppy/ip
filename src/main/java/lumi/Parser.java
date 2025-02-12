package lumi;

/**
 * Parses user input and processes commands in the Lumi application.
 */
public class Parser {
    private static final int MIN_DATE_LENGTH = 10;
    private static final int MIN_WORD_LENGTH_FOR_GERNERAL_COMMAND = 2;
    private static final int MIN_WORD_LENGTH_FOR_DEADLINE = 3;
    private static final int MIN_WORD_LENGTH_FOR_EVENT = 4;


    /**
     * Parses the user input and returns a corresponding Command object.
     *
     * @param input The user input string.
     * @return A Command object corresponding to the input.
     * @throws LumiException If the input is invalid or missing required details.
     */
    public static Command parse(String input) throws LumiException {
        String[] words = input.split(" ");
        String commandWord = words[0];

        switch (commandWord) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        case "todo":
            if (words.length < MIN_WORD_LENGTH_FOR_GERNERAL_COMMAND || words[1].trim().isEmpty()) {
                throw new LumiException("OOPS!!! The description of a todo cannot be empty.");
            }
            return new AddCommand(new Todo(words[1].trim()));
        case "deadline":
            if (words.length < MIN_WORD_LENGTH_FOR_DEADLINE || words[1].trim().isEmpty()) {
                throw new LumiException("OOPS!!! Deadline task requires a description and a date.");
            } else if (!words[2].contains("/by") || words[2].length() < MIN_DATE_LENGTH ) {
                throw new LumiException("OOPS!!! Deadline task needs date.");
            }
            String[] deadlineParts = words[1].split("/by", 2);
            return new AddCommand(new Deadline(deadlineParts[0].trim(), deadlineParts[1].trim()));
        case "delete":
            if (words.length < MIN_WORD_LENGTH_FOR_GERNERAL_COMMAND) {
                throw new LumiException("OOPS!!! Please specify a task number to delete.");
            }
            return new DeleteCommand(Integer.parseInt(words[1].trim()) - 1);
        case "event":
            if (words.length < MIN_WORD_LENGTH_FOR_EVENT || words[1].trim().isEmpty()) {
                throw new LumiException("OOPS!!! Event must have a description and date");
            } else if (!words[2].contains("/from") || !words[3].contains("/to")) {
                throw new LumiException("OOPS!!! Event must have a date");
            }
            String[] eventParts = words[1].split("/from", 2);
            String[] timeParts = eventParts[1].split("/to", 2);
            return new AddCommand(new Event(eventParts[0].trim(), timeParts[0].trim(), timeParts[1].trim()));
        case "unmark":
            if (words.length < 2) {
                throw new LumiException("OOPS!!! Please specify a task number to unmark.");
            }
            return new UnmarkCommand(Integer.parseInt(words[1].trim()) - 1);
        case "find":
            if (words.length < 2 || words[1].trim().isEmpty()) {
                throw new LumiException("OOPS!!! Please provide a keyword to search for.");
            }
            return new FindCommand(words[1].trim());
        case "mark":
            if (words.length < 2) {
                throw new LumiException("OOPS!!! Please specify a task number to mark.");
            }
            return new MarkCommand(Integer.parseInt(words[1].trim()) - 1);
        case"hi":
            return new WelcomeCommand();
        default: throw new LumiException("OOPS!!! I'm sorry, but I don't understand that command.");
            //default: throw new LumiException("OOPS!!! I'm sorry, but I don't understand that command.");
        }
    }

    /**
     * Processes the user command and executes it.
     *
     * @param input The user input string.
     * @param tasks The task list to operate on.
     * @param ui The UI instance for displaying messages.
     * @param storage The storage instance to persist data.
     * @return {@code false} if an ExitCommand is executed, indicating program termination.
     * @throws LumiException If an error occurs while processing the command.
     */
    public static boolean processCommand(String input, TaskList tasks, Ui ui, Storage storage) throws LumiException {
        Command command = parse(input);
        command.execute(tasks, ui, storage);
        return !command.isExit(); // If ExitCommand is executed, this will return false and stop the loop.
    }
}
