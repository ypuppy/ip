package lumi;
/**
 * Represents the main class of the Lumi application, handling initialization and user interactions.
 */
public class Lumi {
    private Storage storage;
    private TaskList tasks;
    private Ui ui = new Ui();
    private Parser parser;

    /**
     * Creates an instance of the Lumi application and loads tasks from the specified file path.
     *
     * @param filePath The file path where tasks are stored.
     */
    public Lumi(String filePath) {
        this.storage = new Storage(filePath);

        try {
            this.tasks = new TaskList(this.storage.loadTasks());
        } catch (LumiException var3) {
            this.ui.showLoadingError();
            this.tasks = new TaskList();
        }

    }

    /**
     * Runs the Lumi application, processing user commands in a loop until termination.
     *
     * @throws LumiException If an error occurs while executing commands.
     */
    public void run() throws LumiException {
        TaskList tasks = new TaskList(this.storage.loadTasks());
        this.ui.showWelcome(tasks);
        boolean isRunning = true;

        while (isRunning) {
            String input = this.ui.readCommand();

            try {
                isRunning = Parser.processCommand(input, tasks, this.ui, this.storage);
            } catch (LumiException var5) {
                LumiException e = var5;
                this.ui.showError(e.getMessage());
            }
        }

        this.ui.showGoodbye();
    }

    /**
     * Processes user input and returns a response string.
     *
     * @param input The user input.
     * @return The response string.
     */
    public String getResponse(String input) {
        try {
            Parser.parse(input).execute(tasks, ui, storage);
            return ui.getLastMessage();
        } catch (LumiException e) {
            return e.getMessage();
        }
    }

    /**
     * The main entry point of the Lumi application.
     *
     * @param args Command-line arguments (not used).
     * @throws LumiException If an error occurs during execution.
     */
    public static void main(String[] args) throws LumiException {
        new Lumi("./src/main/java/Lumi.txt").run();
    }
}

