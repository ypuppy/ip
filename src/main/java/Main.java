import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lumi.Lumi;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Lumi lumi = new Lumi("./src/main/java/Lumi.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("LUMI");
            fxmlLoader.<MainWindow>getController().setLumi(lumi);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
