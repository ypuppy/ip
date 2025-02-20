package lumi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


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
        String testFilePath = "test_lumi.txt";
        storage = new Storage(testFilePath);
        tasks = new TaskList(new ArrayList<>());
        ui = new Ui();
    }

    @Test
    void testWelcomeMessage() {
        ui.showWelcome(tasks);
        String output = outputStream.toString().trim();
        assertTrue(output.contains("HELLO! I'm Lumi~"), "Welcome message should contain Lumi greeting.");
        assertTrue(output.contains("What can I help you?"),
                "Welcome message should prompt for user input.");
        assertTrue(output.contains("You dont have anything in the file T^T."),
                "Welcome message should indicate no saved tasks if list is empty.");
    }

    @Test
    void testListCommandEmpty() {
        tasks = new TaskList();
        String expectedMessage = "OOPS! Your list is empty!";
        assertEquals(expectedMessage, tasks.listTasks(), "List command should return empty list message.");
    }

    @Test
    void testListCommandWithTasks() throws LumiException {
        tasks = new TaskList();
        tasks.getTasks().add(new lumi.Todo("Read a book"));
        tasks.getTasks().add(new lumi.Deadline("Submit report", "2023-12-10"));

        String output = tasks.listTasks();
        assertTrue(output.contains("1. [T][ ] Read a book"), "List should contain the todo task.");
        assertTrue(output.contains("2. [D][ ] Submit report (by: Dec 10 2023)"),
                "List should contain the deadline task.");
    }

    @Test
    void testByeCommand() {
        ui.showGoodbye();
        String output = outputStream.toString().trim();
        assertTrue(output.contains("Well Okay.. Hope to see you again soon T^T"),
                "Bye command should display exit message.");
    }
    @Test
    void testDeleteTask() throws LumiException {
        tasks.addTask(new Todo("Read a book"), ui, storage);
        tasks.addTask(new Deadline("Submit report", "2023-12-10"), ui, storage);
        assertEquals(2, tasks.size(), "There should be 2 tasks before deletion.");

        tasks.deleteTask(0, ui, storage); // Delete first task
        assertEquals(1, tasks.size(), "There should be 1 task left after deletion.");
        assertFalse(tasks.listTasks().contains("Read a book"), "Deleted task should not be in the list.");
    }

    @Test
    void testDeleteInvalidTask() {
        Exception exception = assertThrows(LumiException.class, () -> tasks.deleteTask(5, ui, storage));
        assertTrue(exception.getMessage().contains("OH NO..The task number provided is invalid."),
                "Exception should indicate invalid task number.");
    }

    //handles both mark and unmark test
    @Test
    void testUnmarkTask() throws LumiException {
        Todo todo = new Todo("Finish homework");
        tasks.addTask(todo, ui, storage);
        todo.markAsDone(); // First mark as done
        assertEquals("[T][X] Finish homework", todo.toString(), "Task should be marked as done.");

        todo.unmark(); // Then unmark it
        assertEquals("[T][ ] Finish homework", todo.toString(), "Task should be marked as not done after unmarking.");
    }
}


