import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * @author Tina, Karo and Emma
 */
public class LocalTinder extends Application {
    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        showImage(primaryStage, "file:C:/Users/tinab/OneDrive/Pictures/Skærmbilleder/yo.png");
    }

    public void showImage (Stage stage, String url) {
        ImageView imageView = new ImageView(url);
        imageView.setFitHeight(500);
        imageView.setPreserveRatio(true);
        VBox vBox = new VBox(imageView);
        vBox.setPadding(new Insets(30, 30, 30, 30));
        vBox.setStyle("-fx-background-color: black");
        Scene scene = new Scene(vBox);
        stage.setScene(scene);
        stage.show();
    }
}
