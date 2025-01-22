import java.util.Scanner;

public class Lumi {
    public static void main(String[] args) {
        String logo = " __                \n"
                + "| |    _   _ __ __ __ _________\n"
                + "| |   | | | | |\\ \\/ /| |__  __|\n"
                + "| |__ | |_| | | \\__/ | |_|  |_|\n"
                + "|____/ \\__,_|_|      |_|______|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Lumi");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");

        Scanner scanner = new Scanner(System.in);
        String input;

        while (true) {
            input = scanner.nextLine(); // Read user input
            if (input.equals("bye")) {
                System.out.println("____________________________________________________________");
                System.out.println("    Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                break; // Exit the loop
            } else {
                System.out.println("____________________________________________________________");
                System.out.println("    " + input); // Echo the user's input
                System.out.println("____________________________________________________________");
            }
        }

        scanner.close(); // Close the scanner
    }

}
