package lumi;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;




class FindCommandTest {
    private Ui ui;
    private Storage storage;
    private TaskList tasks;
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outputStream));
        storage = new Storage("test_find.txt");
        tasks = new TaskList(new ArrayList<>());
        ui = new Ui();
    }

    @Test
    void testFindTaskExists() throws LumiException {
        tasks.addTask(new Todo("Read book"), ui, storage);
        tasks.addTask(new Todo("Buy milk"), ui, storage);
        tasks.addTask(new Todo("Return book"), ui, storage);

        FindCommand findCommand = new FindCommand("book");
        findCommand.execute(tasks, ui, storage);

        String output = outputStream.toString().trim();
        assertTrue(output.contains("1. [T][ ] Read book"), "Should find 'Read book'");
        assertTrue(output.contains("2. [T][ ] Return book"), "Should find 'Return book'");
    }

    @Test
    void testFindTaskNotExists() {
        FindCommand findCommand = new FindCommand("piano");
        findCommand.execute(tasks, ui, storage);
        String output = outputStream.toString().trim();
        assertTrue(output.contains("No tasks found matching: piano"), "Should return no results.");
    }
}
