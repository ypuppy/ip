import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * Represents a dialog box consisting of an ImageView to represent the speaker's face
 * and a label containing text from the speaker.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;
    @FXML
    private ImageView memeImage;

    private DialogBox(String text, Image img, Image meme) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        displayPicture.setImage(img);
        if (meme != null) {
            memeImage.setImage(meme);
        } else {
            memeImage.setVisible(false);
        }
        dialog.setText(text);
        displayPicture.setImage(img);

        HBox.setMargin(displayPicture, new Insets(0, 10, 0, 0));

        dialog.setStyle("-fx-background-color: white; -fx-padding: 10px; -fx-border-radius: 5px;");
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }
    /*
    public static DialogBox getUserDialog(String text, Image img) {
        var dbu = new DialogBox(text, img);
        return dbu;
    }

    public static DialogBox getLumiDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        return db;
    }
*/
    public static DialogBox getUserDialog(String text, Image img) {
        var dbu = new DialogBox(text, img, null);
        dbu.getStyleClass().add("user-dialog");
        return dbu;
    }

    public static DialogBox getLumiDialog(String text, Image img, Image meme) {
        var db = new DialogBox(text, img, meme);
        db.getStyleClass().add("lumi-dialog");
        db.flip();
        return db;
    }

    public static DialogBox getLumiDialog(String text, Image img) {
        var db = new DialogBox(text, img, null);
        db.getStyleClass().add("lumi-dialog");
        db.flip();
        return db;
    }






}
