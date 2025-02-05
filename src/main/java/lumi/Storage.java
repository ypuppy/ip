package lumi;

//import java.io.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles loading and saving tasks to a file for persistence.
 */
public class Storage {
    private String filePath;

    /**
     * Initializes a Storage instance with the specified file path.
     *
     * @param filePath The file path to store and retrieve tasks.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }
    // Load tasks from file

    /**
     * Loads tasks from the file.
     *
     * @return A list of tasks loaded from the file.
     * @throws LumiException If an error occurs while parsing the file contents.
     */
    public ArrayList<Task> loadTasks() throws LumiException {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);

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

    /**
     * Saves the list of tasks to the file.
     *
     * @param tasks The list of tasks to save.
     */
    public void saveTasks(ArrayList<Task> tasks) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Task task : tasks) {
                writer.write(formatTaskForFile(task));
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }

    // Convert Task object to string format for file storage

    /**
     * Converts a Task object into a formatted string for file storage.
     *
     * @param task The task to format.
     * @return A formatted string representation of the task.
     */
    private String formatTaskForFile(Task task) {
        String type = task instanceof Todo ? "T"
                : task instanceof Deadline ? "D"
                        : task instanceof Event ? "E" : "?";
        String status = task.isDone ? "1" : "0";

        if (task instanceof Deadline) {
            return type + " | " + status + " | " + task.description + " | " + ((Deadline) task).by.toString();
        } else {
            return type + " | " + status + " | " + task.description;
        }
    }

    // Parse task from file line

    /**
     * Parses a task from a line in the file.
     *
     * @param line The line containing task data.
     * @return The corresponding Task object or null if the line is invalid.
     * @throws LumiException If an error occurs while creating a task.
     */
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
