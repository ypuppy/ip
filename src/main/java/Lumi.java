import java.util.Scanner;
import java.util.ArrayList;

public class Lumi {
    public static void main(String[] args) {
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

        ArrayList<Task> tasks = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        String input;

        while (true) {
            input = scanner.nextLine(); // Read user input
            if (input.equals("bye")) {
                System.out.println("____________________________________________________________");
                System.out.println("    Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                break; // Exit the loop
            } else if (input.equals("list")) {
                System.out.println("____________________________________________________________");
                if (tasks.isEmpty()) {
                    System.out.println("    No tasks added yet!");
                    System.out.println("____________________________________________________________");
                } else {
                    System.out.println("    Here are the tasks in your list:");
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.print("    ");
                        System.out.println((i + 1) + ". " + tasks.get(i)); // Display stored inputs
                    }
                    System.out.println("____________________________________________________________");

                }
            } else if (input.startsWith("mark ")) {
                try {
                    int index = Integer.parseInt(input.split(" ")[1]) - 1;
                    Task task = tasks.get(index);
                    task.markAsDone();
                    System.out.println("____________________________________________________________");
                    System.out.println("    Nice! I've marked this task as done:");
                    System.out.println("      " + task);
                    System.out.println("____________________________________________________________");
                } catch (Exception e) {
                    System.out.println("____________________________________________________________");
                    System.out.println("    Invalid task number!");
                    System.out.println("____________________________________________________________");
                }
            } else if (input.startsWith("unmark ")) {
                try {
                    int index = Integer.parseInt(input.split(" ")[1]) - 1;
                    Task task = tasks.get(index);
                    task.unmark();
                    System.out.println("____________________________________________________________");
                    System.out.println("    OK, I've marked this task as not done yet:");
                    System.out.println("      " + task);
                    System.out.println("____________________________________________________________");
                } catch (Exception e) {
                    System.out.println("____________________________________________________________");
                    System.out.println("Invalid task number!");
                    System.out.println("____________________________________________________________");
                }
            } else if (input.startsWith("todo ")) {
                String description = input.substring(5).trim();
                Task task = new Todo(description);
                tasks.add(task);
                System.out.println("____________________________________________________________");
                System.out.println("    Got it. I've added this task:");
                System.out.println("      " + task);
                System.out.println("    Now you have " + tasks.size() + " tasks in the list.");
                System.out.println("____________________________________________________________");
            }else if (input.startsWith("deadline ")) {
                String[] parts = input.substring(9).split(" /by ");
                String description = parts[0].trim();
                String by = parts[1].trim();
                Task task = new Deadline(description, by);
                tasks.add(task);
                System.out.println("____________________________________________________________");
                System.out.println("    Got it. I've added this task:");
                System.out.println("      " + task);
                System.out.println("    Now you have " + tasks.size() + " tasks in the list.");
                System.out.println("____________________________________________________________");
            } else if (input.startsWith("event ")) {
                String[] parts = input.substring(6).split(" /from ");
                String description = parts[0].trim();
                String[] times = parts[1].split(" /to ");
                String from = times[0].trim();
                String to = times[1].trim();
                Task task = new Event(description, from, to);
                tasks.add(task);
                System.out.println("____________________________________________________________");
                System.out.println("    Got it. I've added this task:");
                System.out.println("      " + task);
                System.out.println("    Now you have " + tasks.size() + " tasks in the list.");
                System.out.println("____________________________________________________________");
            } else {
                System.out.println("____________________________________________________________");
                System.out.println("    I don't understand that command.");
                System.out.println("____________________________________________________________");
            }

        }

        scanner.close(); // Close the scanner
    }

}
