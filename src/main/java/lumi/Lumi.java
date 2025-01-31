package lumi;

public class Lumi {
    private Storage storage;
    private TaskList tasks;
    private Ui ui = new Ui();
    private Parser parser;

    public Lumi(String filePath) {
        this.storage = new Storage(filePath);

        try {
            this.tasks = new TaskList(this.storage.loadTasks());
        } catch (LumiException var3) {
            this.ui.showLoadingError();
            this.tasks = new TaskList();
        }

    }

    public void run() throws LumiException {
        TaskList tasks = new TaskList(this.storage.loadTasks());
        this.ui.showWelcome(tasks);
        boolean isRunning = true;

        while(isRunning) {
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

    public static void main(String[] args) throws LumiException {
        (new Lumi("./src/main/java/Lumi.txt")).run();
    }
}

