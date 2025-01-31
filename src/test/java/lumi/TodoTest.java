package lumi;

import lumi.Todo;
import lumi.Task;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TodoTest {

    @Test
    void testTodoCreation() {
        Todo todo = new Todo("Read a book");
        assertNotNull(todo, "Todo should not be null.");
        assertEquals("Read a book", todo.getDescription(), "Todo description should match.");
      //  assertFalse(todo.isDone(), "Newly created Todo should not be marked as done.");
    }

    @Test
    void testMarkTodoAsDone() {
        Todo todo = new Todo("Read a book");
        todo.markAsDone();
     //   assertTrue(todo.isDone(), "Todo should be marked as done.");
        assertEquals("[T][X] Read a book", todo.toString(), "String representation should show task as done.");
    }

    @Test
    void testUnmarkTodo() {
        Todo todo = new Todo("Read a book");
        todo.markAsDone();
        todo.unmark();
     //   assertFalse(todo.isDone(), "Todo should be marked as not done.");
        assertEquals("[T][ ] Read a book", todo.toString(), "String representation should show task as not done.");
    }

    @Test
    void testTodoStringFormat() {
        Todo todo = new Todo("Read a book");
        assertEquals("[T][ ] Read a book", todo.toString(), "Todo string format should be correct.");
    }
}

