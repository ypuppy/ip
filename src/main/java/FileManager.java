import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class FileManager {
    private static final String FILE_PATH = "./src/main/java/Lumi.txt";

    // Load tasks from file
    public static ArrayList<Task> loadTasks() {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(FILE_PATH);

        if (!file.exists()) {
            try {
                file.getParentFile().mkdirs();
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
        } catch (FileNotFoundException | LumiException e) {
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
            return type + " | " + status + " | " + task.description + " | " + ((Deadline) task).by.toString();
        } else {
            return type + " | " + status + " | " + task.description;
        }
    }

    // Parse task from file line
    private static Task parseTaskFromFile(String line) throws LumiException {
        String[] parts = line.split(" \\| ");
        if (parts.length < 3) {
            return null;
        }

        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        Task task = null;

        if ("T".equals(type)) {
            task = new Todo(description);
        } else if ("D".equals(type) && parts.length >= 4) {
            task = new Deadline(description, parts[3]); // Deadline constructor handles errors
        }

        if (task != null && isDone) {
            task.markAsDone();
        }

        return task;
    }
}
