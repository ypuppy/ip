package lumi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;





class EventTest {

    @Test
    void createEvent_validDates_success() throws LumiException {
        Event event = new Event("Birthday Party", "2024-06-01", "2024-06-02");
        assertEquals("[E][ ] Birthday Party (from: Jun 01 2024 to: Jun 02 2024)", event.toString());
    }

    @Test
    void createEvent_invalidDateFormat_throwsLumiException() {
        Exception exception = assertThrows(LumiException.class, () ->
                new Event("Meeting", "June 5, 2024", "2024-06-06") // Invalid date format
        );
        assertEquals("OOPS.. Sorry..Please use yyyy-MM-dd (e.g., 2019-12-02).", exception.getMessage());
    }

    @Test
    void createEventCommand_addsEventToTaskList() throws LumiException {
        TaskList taskList = new TaskList(new ArrayList<>());
        Ui ui = new Ui();
        Storage storage = new Storage("test.txt"); // Mocked storage (no actual file operations)

        Event event = new Event("Conference", "2025-01-10", "2025-01-12");
        EventCommand command = new EventCommand(event);

        command.execute(taskList, ui, storage);

        assertEquals(1, taskList.size()); // Ensure task was added
        assertEquals("[E][ ] Conference (from: Jan 10 2025 to: Jan 12 2025)", taskList.get(0).toString());
    }
}
