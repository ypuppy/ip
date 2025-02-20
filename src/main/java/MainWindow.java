import javafx.application.Platform;
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
    @FXML
    private Button scrollDownButton;

    private Lumi lumi;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/image/user1.png"));
    private Image lumiImage = new Image(this.getClass().getResourceAsStream("/image/bot2.png"));
    private Image memeImage = new Image(getClass().getResourceAsStream("/image/meme1.png"));

    /**
     * initialize the MainWindow to show the welcome message
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().addListener((observable, oldValue, newValue) -> {
            boolean isAtBottom = (double) newValue == 1.0;
            scrollDownButton.setVisible(!isAtBottom);
        });
        showWelcomeMessage();

    }


    /**
     * Displays a welcome message from Lumi in the dialog box when the application starts.
     */
    private void showWelcomeMessage() {
        String welcomeMessage = "HELLO! I'm Lumi~.\nWhat can i help you?";
        dialogContainer.getChildren().add(
                DialogBox.getLumiDialog(welcomeMessage, lumiImage, memeImage)
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
        Image selectedMeme = null;
        String response = lumi.getResponse(input);

        if (input.equalsIgnoreCase("hi")) {
            selectedMeme = new Image(getClass().getResourceAsStream("/image/meme2.png"));
        } else if (input.equalsIgnoreCase("list")) {
            selectedMeme = new Image(getClass().getResourceAsStream("/image/meme3.png"));
        } else if (input.equalsIgnoreCase("bye")) {
            selectedMeme = new Image(getClass().getResourceAsStream("/image/meme4.png"));
        } else if (input.contains("todo")) {
            selectedMeme = new Image(getClass().getResourceAsStream("/image/meme6.png"));
        } else if (input.contains("event")) {
            selectedMeme = new Image(getClass().getResourceAsStream("/image/meme7.png"));
        } else if (input.contains("deadline")) {
            selectedMeme = new Image(getClass().getResourceAsStream("/image/meme9.png"));
        } else {
            selectedMeme = new Image(getClass().getResourceAsStream("/image/meme10.png"));
        }
        if (response.contains("OH NO!") || response.contains("OOPS!")) {
            selectedMeme = new Image(getClass().getResourceAsStream("/image/meme8.png"));
        } else if (response.contains("Hrm..") || response.contains("Hrmm")) {
            selectedMeme = new Image(getClass().getResourceAsStream("/image/meme5.png"));
        }
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getLumiDialog(response, lumiImage, selectedMeme)
        );
        userInput.clear();
        Platform.runLater(() -> scrollToBottom());
    }

    @FXML
    private void scrollToBottom() {
        scrollPane.setVvalue(1.0);
    }
}
