public class Lumi {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    public Lumi(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        parser = new Parser();
        try {
            tasks = new TaskList(storage.loadTasks());
        } catch (LumiException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() throws LumiException {
        TaskList tasks = new TaskList(storage.loadTasks());
        ui.showWelcome(tasks);

        boolean isRunning = true;

        while (isRunning) {
            String input = ui.readCommand();
            try {
                isRunning = parser.processCommand(input, tasks, ui, storage);
            } catch (LumiException e) {
                ui.showError(e.getMessage());
            }
        }

        ui.showGoodbye();
    }

    public static void main(String[] args) throws LumiException {
        new Lumi("./src/main/java/Lumi.txt").run();
    }
}
