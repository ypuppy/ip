package lumi;

import java.util.Scanner;

/**
 * Handles user interactions, including displaying messages and reading input.
 */
public class Ui {
    private Scanner scanner;
    private String lastMessage = "";

    /**
     * Initializes the UI with a new scanner for reading user input.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Displays the welcome message and any previously saved tasks.
     *
     * @param tasks The task list containing previously saved tasks.
     */
    public void showWelcome(TaskList tasks) {
        String logo = " __\n"
                + "| |    _   _ __ __ __ _________\n"
                + "| |   | | | | |\\ \\/ /| |__  __|\n"
                + "| |__ | |_| | | \\__/ | |_|  |_|\n"
                + "|____/ \\__,_|_|      |_|______|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Lumi");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");
        if (!tasks.isEmpty()) {
            System.out.println("Previously saved tasks:");
            System.out.println("____________________________________________________________");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println("    " + (i + 1) + ". " + tasks.get(i));
            }
            System.out.println("____________________________________________________________");
        } else {
            System.out.println("No saved tasks found.");
        }



    }

    /**
     * Displays a horizontal line for UI formatting.
     */
    public void showLine() {
        System.out.println("____________________________________________________________");
    }


    /**
     * Reads the next command input from the user.
     *
     * @return The user input as a string.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Displays the goodbye message when the program terminates.
     */
    public void showGoodbye() {
        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays an error message for task loading failures.
     */
    public void showLoadingError() {
        System.out.println("Error loading tasks. Starting with an empty list.");
    }

    /**
     * Displays an error message with a given message.
     *
     * @param message The error message to display.
     */
    public void showError(String message) {
        System.out.println("____________________________________________________________");
        System.out.println("OOPS!!! " + message);
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays a given message to the user.
     *
     * @param message The message to display.
     */
    public void showMessage(String message) {
        System.out.println("____________________________________________________________");
        System.out.println(message);
        System.out.println("____________________________________________________________");
        lastMessage = message;
    }

    /**
     * Displays the results of a find operation.
     *
     * @param message The message containing search results.
     */
    public void showFindResults(String message) {
        System.out.println("____________________________________________________________");
        System.out.println(message);
        System.out.println("____________________________________________________________");
        lastMessage = message;
    }

    /**
     * Retrieves the last displayed message.
     *
     * @return The last stored message.
     */
    public String getLastMessage() {
        return lastMessage;
    }
}

