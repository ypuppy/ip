import java.util.Scanner;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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

        ArrayList<Task> tasks = FileManager.loadTasks();
        //ArrayList<Task> tasks = new ArrayList<>();

        // Print tasks if any exist
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


        Scanner scanner = new Scanner(System.in);
        String input;

        while (true) {
            input = scanner.nextLine(); // Read user input
            if (input.equals("bye")) {
                System.out.println("____________________________________________________________");
                System.out.println("    Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                FileManager.saveTasks(tasks);
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
                    FileManager.saveTasks(tasks);
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
                    FileManager.saveTasks(tasks);
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
                    FileManager.saveTasks(tasks);
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
            } else if (input.startsWith("delete")) {
                try {
                    // Extract the index from the input
                    String indexStr = input.substring(6).trim();
                    int index = Integer.parseInt(indexStr) - 1; // Convert to zero-based index

                    // Check if the index is valid
                    if (index < 0 || index >= tasks.size()) {
                        throw new LumiException("    OOPS!!! The task number provided is invalid.");
                    }

                    // Remove the task and provide feedback
                    Task removedTask = tasks.remove(index);
                    FileManager.saveTasks(tasks);
                    System.out.println("____________________________________________________________");
                    System.out.println("    Noted. I've removed this task:");
                    System.out.println("      " + removedTask);
                    System.out.println("    Now you have " + tasks.size() + " tasks in the list.");
                    System.out.println("____________________________________________________________");
                } catch (NumberFormatException e) {
                    // Handle non-integer input
                    System.out.println("____________________________________________________________");
                    System.out.println("    OOPS!!! Please provide a valid task number to delete.");
                    System.out.println("____________________________________________________________");
                } catch (LumiException e) {
                    // Handle invalid task number
                    System.out.println("____________________________________________________________");
                    System.out.println(e.getMessage());
                    System.out.println("____________________________________________________________");
                } catch (Exception e) {
                    // Catch any unexpected errors
                    System.out.println("____________________________________________________________");
                    System.out.println("An unexpected error occurred: " + e.getMessage());
                    System.out.println("____________________________________________________________");
                }
            } else if (input.startsWith("find ")) {
                LocalDate date = LocalDate.parse(input.substring(5).trim()); // Parse input date
                System.out.println("____________________________________________________________");
                System.out.println("Tasks on " + date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ":");
                boolean found = false;

                for (Task task : tasks) {
                    if (task instanceof Deadline && ((Deadline) task).by.equals(date)) {
                        System.out.println("  " + task);
                        found = true;
                    }
                }

                if (!found) {
                    System.out.println("  No tasks found on this date.");
                }

                System.out.println("____________________________________________________________");

            } else {
                System.out.println("____________________________________________________________");
                System.out.println("    Sorry,I don't understand that command.");
                System.out.println("____________________________________________________________");
            }

        }

        scanner.close(); // Close the scanner
    }

}
