package lumi;

import lumi.Lumi;
import lumi.Ui;
import lumi.Parser;
import lumi.TaskList;
import lumi.Storage;
import lumi.LumiException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CommandTest {
    private Ui ui;
    private Storage storage;
    private TaskList tasks;
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() throws LumiException {
        // Redirect system output to capture console logs for testing
        System.setOut(new PrintStream(outputStream));

        // Initialize components
        String testFilePath = "test_lumi.txt";  // Temporary test file
        storage = new Storage(testFilePath);
        tasks = new TaskList(new ArrayList<>());  // Start with an empty list
        ui = new Ui();
    }

    @Test
    void testWelcomeMessage() {
        ui.showWelcome(tasks);
        String output = outputStream.toString().trim();
        assertTrue(output.contains("Hello! I'm Lumi"), "Welcome message should contain Lumi greeting.");
        assertTrue(output.contains("What can I do for you?"), "Welcome message should prompt for user input.");
        assertTrue(output.contains("No saved tasks found."), "Welcome message should indicate no saved tasks if list is empty.");
    }

    @Test
    void testListCommandEmpty() {
        tasks = new TaskList();  // Empty list
        String expectedMessage = "Your list is empty!";
        assertEquals(expectedMessage, tasks.listTasks(), "List command should return empty list message.");
    }

    @Test
    void testListCommandWithTasks() throws LumiException {
        tasks = new TaskList();
        tasks.getTasks().add(new lumi.Todo("Read a book"));
        tasks.getTasks().add(new lumi.Deadline("Submit report", "2023-12-10"));

        String output = tasks.listTasks();
        assertTrue(output.contains("1. [T][ ] Read a book"), "List should contain the todo task.");
        assertTrue(output.contains("2. [D][ ] Submit report (by: Dec 10 2023)"), "List should contain the deadline task.");
    }

    @Test
    void testByeCommand() {
        ui.showGoodbye();
        String output = outputStream.toString().trim();
        assertTrue(output.contains("Bye. Hope to see you again soon!"), "Bye command should display exit message.");
    }
}
