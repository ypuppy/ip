import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import lumi.Lumi;

/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Lumi lumi;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/image/user.png"));
    private Image lumiImage = new Image(this.getClass().getResourceAsStream("/image/bot.png"));

    /**
     * initialize the MainWindow to show the welcome message
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        showWelcomeMessage();
    }


    /**
     * Displays a welcome message from Lumi in the dialog box when the application starts.
     */
    private void showWelcomeMessage() {
        String welcomeMessage = "Hello! I'm Lumi.\nHow can I assist you today?";
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(welcomeMessage, lumiImage)
        );
    }

    /** Injects the Duke instance */
    public void setLumi(Lumi bot) {
        lumi = bot;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = lumi.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, lumiImage)
        );
        userInput.clear();
    }
}
