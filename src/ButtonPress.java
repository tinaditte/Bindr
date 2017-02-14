import javafx.application.Application;
import javafx.stage.Stage;

/**
 * @author Mathias
 */
public class ButtonPress extends Application {
    public static void main(String [] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        /*
        Button no1 = new Button("Delete");
        no1.setOnAction(event -> {
            try {
                Files.delete(path); //tager i mod en "Path" fil (fra et array?)
            } catch (NoSuchFileException x) {
                System.err.format("%s: no such" + " file or directory%n", path);
            } catch (DirectoryNotEmptyException x) {
                System.err.format("%s not empty%n", path);
            } catch (IOException x) {
                System.err.println(x);
            }

            showNext();
        });

        Button no2 = new Button("Keep");
        no2.setOnAction(event -> showNext());

        VBox vBox = new VBox();
        vBox.getChildren().addAll(no1,no2);

        Scene scene = new Scene(vBox);
        primaryStage.setScene(scene);
        primaryStage.show();
        */
    }

    private void showNext() { // Ved klik, gå et skridt videre i arrayet
        /*
        int i = 0;

        while (ListOfFile().length > i) {
            ListOfFiles[i];
            i++;
        }
        */
    }
}