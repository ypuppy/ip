import java.util.Scanner;

public class Ui {
    private Scanner scanner;


    public Ui() {
        scanner = new Scanner(System.in);
    }

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

    public String readCommand() {
        return scanner.nextLine();
    }

    public void showGoodbye() {
        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }

    public void showLoadingError() {
        System.out.println("Error loading tasks. Starting with an empty list.");
    }

    public void showError(String message) {
        System.out.println("____________________________________________________________");
        System.out.println("OOPS!!! " + message);
        System.out.println("____________________________________________________________");
    }

    public void showMessage(String message) {
        System.out.println("____________________________________________________________");
        System.out.println(message);
        System.out.println("____________________________________________________________");
    }
}

