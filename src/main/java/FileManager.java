import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class FileManager {
    private static final String FILE_PATH = "./src/main/java/test.txt";

    // Load tasks from file at startup
    public static ArrayList<Task> loadTasks() {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(FILE_PATH);

        if (!file.exists()) {
            // Create the file and parent directories if they don't exist
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("Error creating save file: " + e.getMessage());
            }
            return tasks;
        }

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Task task = parseTaskFromFile(line);
                if (task != null) {
                    tasks.add(task);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error loading tasks: File not found.");
        }

        return tasks;
    }

    // Save tasks to file
    public static void saveTasks(ArrayList<Task> tasks) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Task task : tasks) {
                writer.write(formatTaskForFile(task));
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }

    // Convert Task object to string format for file storage
    private static String formatTaskForFile(Task task) {
        String type = task instanceof Todo ? "T" :
                task instanceof Deadline ? "D" :
                        task instanceof Event ? "E" : "?";
        String status = task.isDone ? "1" : "0";

        if (task instanceof Deadline) {
            return type + " | " + status + " | " + task.description + " | " + ((Deadline) task).by;
        } else if (task instanceof Event) {
            return type + " | " + status + " | " + task.description + " | " + ((Event) task).from + " | " + ((Event) task).to;
        } else {
            return type + " | " + status + " | " + task.description;
        }
    }

    // Parse task from file line
    private static Task parseTaskFromFile(String line) {
        String[] parts = line.split(" \\| ");
        if (parts.length < 3) {
            return null;
        }

        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        Task task = null;
        switch (type) {
            case "T":
                task = new Todo(description);
                break;
            case "D":
                if (parts.length >= 4) {
                    task = new Deadline(description, parts[3]);
                }
                break;
            case "E":
                if (parts.length >= 5) {
                    task = new Event(description, parts[3], parts[4]);
                }
                break;
        }

        if (task != null && isDone) {
            task.markAsDone();
        }
        return task;
    }
}

