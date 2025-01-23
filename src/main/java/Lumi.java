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
                try {
                    System.out.println("____________________________________________________________");
                    if (tasks.isEmpty()) {
                        throw new LumiException("OOPS!!! Your list is empty.");
                    }
                    System.out.println("    Here are the tasks in your list:");
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.print("    ");
                        System.out.println((i + 1) + ". " + tasks.get(i)); // Display stored inputs
                    }
                    System.out.println("____________________________________________________________");
                } catch (LumiException e) {
                    System.out.println(e.getMessage());
                    System.out.println("____________________________________________________________");
                }
                /*
                System.out.println("____________________________________________________________");
                if (tasks.isEmpty()) {
                    throw new LumiException("OOPS!!! Your list is empty.\n____________________________________________________________\n");
                } else {
                System.out.println("    Here are the tasks in your list:");
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.print("    ");
                    System.out.println((i + 1) + ". " + tasks.get(i)); // Display stored inputs
                }
            }*/
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
                    System.out.println("    Invalid task number!");
                    System.out.println("____________________________________________________________");
                }
            } else if (input.startsWith("todo")) {
                try {
                    String description = input.substring(4).trim();
                    if (description.isEmpty()) {
                        throw new LumiException("OOPS!!! The description of a todo cannot be empty.");
                    }
                    Task task = new Todo(description);
                    tasks.add(task);
                    System.out.println("____________________________________________________________");
                    System.out.println("    Got it. I've added this task:");
                    System.out.println("      " + task);
                    System.out.println("    Now you have " + tasks.size() + " tasks in the list.");
                    System.out.println("____________________________________________________________");
                } catch (LumiException e) {
                    System.out.println("____________________________________________________________");
                    System.out.println(e.getMessage());
                    System.out.println("____________________________________________________________");
                }
            }
/*                String description = input.substring(5).trim();
                Task task = new Todo(description);
                tasks.add(task);
                System.out.println("____________________________________________________________");
                System.out.println("    Got it. I've added this task:");
                System.out.println("      " + task);
                System.out.println("    Now you have " + tasks.size() + " tasks in the list.");
                System.out.println("____________________________________________________________");

            else if (input.startsWith("deadline ")) {
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

 */
            else if (input.startsWith("deadline")) {
                try {
                    String[] parts = input.substring(8).split("\\s*/by\\s*");
                    if (parts[0].trim().isEmpty()) {
                        throw new LumiException("OOPS!!! The description of the task cannot be empty.");
                    } else if ( parts.length < 2 || parts[1].trim().isEmpty()) {
                        throw new LumiException("OOPS!!! what is the deadline?");
                    }
                    String description = parts[0].trim();
                    String by = parts[1].trim();
                    Task task = new Deadline(description, by);
                    tasks.add(task);
                    System.out.println("____________________________________________________________");
                    System.out.println("    Got it. I've added this task:");
                    System.out.println("      " + task);
                    System.out.println("    Now you have " + tasks.size() + " tasks in the list.");
                    System.out.println("____________________________________________________________");
                } catch (LumiException e) {
                    System.out.println("____________________________________________________________");
                    System.out.println(e.getMessage());
                    System.out.println("____________________________________________________________");
                } catch (Exception e) {
                    System.out.println("____________________________________________________________");
                    System.out.println("An unexpected error occurred: " + e.getMessage());
                    System.out.println("____________________________________________________________");
                }
            } else if (input.startsWith("event")) {
                try {
                    String[] parts = input.substring(5).split("\\s*/from\\s*");
                    if (parts[0].trim().isEmpty()) {
                        throw new LumiException("OOPS!!! The description of an event cannot be empty.");
                    }
                    String description = parts[0].trim();
                    String[] times = parts[1].split("\\s*/to\\s*");
                    if (times.length < 2 || times[0].trim().isEmpty() || times[1].trim().isEmpty()) {
                        throw new LumiException("OOPS!!! The start or end time of an event cannot be empty.");
                    }
                    String from = times[0].trim();
                    String to = times[1].trim();
                    Task task = new Event(description, from, to);
                    tasks.add(task);
                    System.out.println("____________________________________________________________");
                    System.out.println("    Got it. I've added this task:");
                    System.out.println("      " + task);
                    System.out.println("    Now you have " + tasks.size() + " tasks in the list.");
                    System.out.println("____________________________________________________________");
                } catch (LumiException e) {
                    System.out.println("____________________________________________________________");
                    System.out.println(e.getMessage());
                    System.out.println("____________________________________________________________");
                } catch (Exception e) {
                    System.out.println("____________________________________________________________");
                    System.out.println("An unexpected error occurred: " + e.getMessage());
                    System.out.println("____________________________________________________________");
                }
            } else {
                System.out.println("____________________________________________________________");
                System.out.println("    I don't understand that command.");
                System.out.println("____________________________________________________________");
            }

        }

        scanner.close(); // Close the scanner
    }

}
