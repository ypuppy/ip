package lumi;

import java.util.Set;

/**
 * Parses user input and processes commands in the Lumi application.
 */
public class Parser {
    private static final int MIN_WORD_LENGTH_FOR_GERNERAL_COMMAND = 2;
    private static final Set<String> SINGLE_WORD_COMMANDS = Set.of("bye", "list", "hi");


    /**
     * Parses the user input and returns a corresponding Command object.
     *
     * @param input The user input string.
     * @return A Command object corresponding to the input.
     * @throws LumiException If the input is invalid or missing required details.
     */
    public static Command parse(String input) throws LumiException {
        String[] words = input.split("\\s+", 2);
        String commandWord = words[0];

        if (words.length < MIN_WORD_LENGTH_FOR_GERNERAL_COMMAND && !SINGLE_WORD_COMMANDS.contains(commandWord)) {
            throw new LumiException("Hrm.. what exactly do i need to do for the command?");
        }
        switch (commandWord) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        case "todo":
            assert words.length >= MIN_WORD_LENGTH_FOR_GERNERAL_COMMAND : "Todo command must have a description";
            if (words[1].trim().isEmpty()) {
                throw new LumiException("OOPS!!! The description of a todo cannot be empty.");
            }
            return new AddCommand(new Todo(words[1].trim()));
        case "deadline":
            if (words[1].trim().isEmpty()) {
                throw new LumiException("OOPS!!! Deadline task requires a description and a date.");
            }
            String[] deadlineParts = words[1].split("/by", 2);
            if (deadlineParts[1].isEmpty()) {
                throw new LumiException("OOPS!!! Deadline task needs date.");
            }
            return new AddCommand(new Deadline(deadlineParts[0].trim(), deadlineParts[1].trim()));
        case "delete":
            if (words[1].trim().isEmpty()) {
                throw new LumiException("OOPS!!! Please specify a task number to delete.");
            }
            return new DeleteCommand(Integer.parseInt(words[1].trim()) - 1);
        case "event":
            if (words[1].trim().isEmpty()) {
                throw new LumiException("OOPS!!! Event must have a description and date");
            } else if (!words[1].contains("/from") || !words[1].contains("/to")) {
                throw new LumiException("OOPS!!! Event must have a start date and an end date");
            }
            String[] eventParts = words[1].split("/from", 2);
            String[] timeParts = eventParts[1].split("/to", 2);
            if (eventParts[0].isEmpty()) {
                throw new LumiException("what is the event about?");
            } else if (eventParts[1].isEmpty()) {
                throw new LumiException("when will the event start?");
            } else if (timeParts[1].isEmpty()) {
                throw new LumiException("when will the event when?");
            }
            return new AddCommand(new Event(eventParts[0].trim(), timeParts[0].trim(), timeParts[1].trim()));
        case "unmark":
            if (words[1].trim().isEmpty()) {
                throw new LumiException("OOPS!!! Please specify a task number to unmark.");
            }
            return new UnmarkCommand(Integer.parseInt(words[1].trim()) - 1);
        case "find":
            if (words[1].trim().isEmpty()) {
                throw new LumiException("OOPS!!! Please provide a keyword to search for.");
            }
            return new FindCommand(words[1].trim());
        case "mark":
            if (words[1].trim().isEmpty()) {
                throw new LumiException("OOPS!!! Please specify a task number to mark.");
            }
            return new MarkCommand(Integer.parseInt(words[1].trim()) - 1);
        case "tag":
            if (!words[1].trim().contains("#")) {
                throw new LumiException("OOPS!!! Please specify a task number to tag. and use # for tagging");
            }
            String[] tagParts = words[1].split("#", 2);
            if (tagParts[1].isEmpty()) {
                throw new LumiException("what do you want to tag?");
            }
            try {
                int taskNumber = Integer.parseInt(tagParts[0].trim()) - 1;
                String tag = tagParts[1].trim();
                return new TagCommand(taskNumber, tag);
            } catch (NumberFormatException e) {
                throw new LumiException("OOPS!!! Task number must be a valid number.");
            }
        case"hi":
            return new WelcomeCommand();
        default: throw new LumiException("Hrmm.. I don't understand that command.");
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
        assert tasks != null : "TaskList should not be null";
        assert ui != null : "UI should not be null";
        assert storage != null : "Storage should not be null";

        Command command = parse(input);
        command.execute(tasks, ui, storage);
        return !command.isExit(); // If ExitCommand is executed, this will return false and stop the loop.
    }
}
