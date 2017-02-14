import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * @author Mikkel
 */
public class NoMoreImagesDialog extends Application {
    public void start(Stage primaryStage) {
        Stage window;
        Button button;

        window = primaryStage;
        window.setTitle("Window");

        button = new Button("Click me!");
        button.setOnAction(e -> folderEmpty());

        StackPane layout = new StackPane();
        layout.getChildren().add(button);
        Scene scene = new Scene(layout, 300, 250);

        window.setScene(scene);
        window.show();
    }

    // Add to loop method
    public void isEmpty(boolean isEmpty) {
        if (isEmpty) {
            folderEmpty();
            // stop loop
        } else {
            // continue loop
        }
    }

    public void folderEmpty() {
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Window");
        window.setMinWidth(250);

        Label label = new Label();
        label.setText("Folder is empty. THUMPS UP!");
        Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> window.close());

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, closeButton);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }
}